package Olumgollum_Project.RoomInfo;

import java.sql.Date;

public class RoomInfoVo {
    private int house_num;
    private String user_id;
    private String photo_url;
    private String trade_method;
    private int deposit;
    private int monthly;
    private int jeonsegeum;
    private int sale_price;
    private String area;
    private Date accept_date;
    private String address;
    private String floor1;
    private int phonenumber;
    private Date regit_date;
    private String remark;

    public RoomInfoVo(int house_num, String user_id, String photo_url, String trade_method, int deposit, int monthly, int jeonsegeum, int sale_price, String area, Date accept_date, String address, String floor1, int phonenumber, Date regit_date, String remark) {
        this.house_num = house_num;
        this.user_id = user_id;
        this.photo_url = photo_url;
        this.trade_method = trade_method;
        this.deposit = deposit;
        this.monthly = monthly;
        this.jeonsegeum = jeonsegeum;
        this.sale_price = sale_price;
        this.area = area;
        this.accept_date = accept_date;
        this.address = address;
        this.floor1 = floor1;
        this.phonenumber = phonenumber;
        this.regit_date = regit_date;
        this.remark = remark;
    }

    public int getHouse_num() {
        return house_num;
    }

    public void setHouse_num(int house_num) {
        this.house_num = house_num;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getTrade_method() {
        return trade_method;
    }

    public void setTrade_method(String trade_method) {
        this.trade_method = trade_method;
    }


    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getMonthly() {
        return monthly;
    }

    public void setMonthly(int monthly) {
        this.monthly = monthly;
    }

    public int getJeonsegeum() {
        return jeonsegeum;
    }

    public void setJeonsegeum(int jeonsegeum) {
        this.jeonsegeum = jeonsegeum;
    }

    public int getsale_price() {
        return sale_price;
    }

    public void setsale_price(int sale_price) {
        sale_price = sale_price;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getAccept_date() {
        return accept_date;
    }

    public void setAccept_date(Date accept_date) {
        this.accept_date = accept_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFloor1() {
        return floor1;
    }

    public void setFloor1(String floor1) {
        this.floor1 = floor1;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Date getRegit_date() {
        return regit_date;
    }

    public void setRegit_date(Date regit_date) {
        this.regit_date = regit_date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
