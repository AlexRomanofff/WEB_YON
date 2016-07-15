package com.yon.dao;

import java.util.List;

public interface DAO {
    List selectYonsByUser(String User);
    void updateYonState(String simNumber, String roomNumber);

}
