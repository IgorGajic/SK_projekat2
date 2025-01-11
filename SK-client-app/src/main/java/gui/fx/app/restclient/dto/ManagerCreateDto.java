package gui.fx.app.restclient.dto;

import java.sql.Date;

public class ManagerCreateDto extends UserCreateDto{
    private Date hireDate;

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
}
