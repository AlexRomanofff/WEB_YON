package com.yon.dao;

import com.yon.model.Action;
import com.yon.model.State;
import com.yon.model.User;
import com.yon.model.Yon;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.util.List;

public class DAOImplements implements DAO{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }
    @Transactional
    public List<Yon> selectYonsByUser(String user) {

        Session session = sessionFactory.openSession();
        User login = getUserFromDB(user, session);
        Criteria criteria = session.createCriteria(Yon.class);
        criteria.add(Restrictions.eq("user", login));

        return criteria.list();
    }



    @Transactional
    public void updateYonState(String simNumber, String roomNumber) {

        Session session = sessionFactory.openSession();
        Yon yon = getYonBySimNumber(simNumber, session);
        State state = changeYonState(yon.getState());
        yon.setState(state);
        insertToActionTable(roomNumber, session, yon);
        updateYonTable(session, yon);

        }

    private Yon getYonBySimNumber(String simNumber, Session session) {
        Criteria criteria = session.createCriteria(Yon.class);
        criteria.add(Restrictions.eq("simNumber", simNumber));
        return (Yon)criteria.uniqueResult();
    }

    private void insertToActionTable(String roomNumber, Session session, Yon yon) {
        Action action = new Action();
        action.setDate(new Date(System.currentTimeMillis()));
        action.setAttribute(roomNumber);
        action.setYonID(yon);
        action.setStatus(yon.getState());

        session.save(action);
    }

    private void updateYonTable(Session session, Yon yon) {
        try {
            session.beginTransaction();
            session.update(yon);
            session.getTransaction().commit();
        }catch(HibernateException ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    private State changeYonState(State state) {
        if (state.equals(State.STANDBY)) {
            state = State.ACTIVATED;
        } else {
            state = State.STANDBY;
        }
        return state;
    }

    private User getUserFromDB(String user, Session session) {
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", user));
        return (User)criteria.uniqueResult();
    }
}
