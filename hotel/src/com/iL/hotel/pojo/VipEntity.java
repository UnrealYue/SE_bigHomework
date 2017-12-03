package com.iL.hotel.pojo;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "vip", schema = "hotel", catalog = "")
public class VipEntity {
    private int id;
    private Date birthday;
    private String job;
    private double balance;
    private int totalCosts;
    private GuestEntity guestByGuestId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "job", nullable = false, length = 255)
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Basic
    @Column(name = "balance", nullable = false, precision = 0)
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "total_costs", nullable = false)
    public int getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(int totalCosts) {
        this.totalCosts = totalCosts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VipEntity vipEntity = (VipEntity) o;

        if (id != vipEntity.id) return false;
        if (Double.compare(vipEntity.balance, balance) != 0) return false;
        if (totalCosts != vipEntity.totalCosts) return false;
        if (birthday != null ? !birthday.equals(vipEntity.birthday) : vipEntity.birthday != null) return false;
        if (job != null ? !job.equals(vipEntity.job) : vipEntity.job != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + totalCosts;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "guest_id", nullable = false)
    public GuestEntity getGuestByGuestId() {
        return guestByGuestId;
    }

    public void setGuestByGuestId(GuestEntity guestByGuestId) {
        this.guestByGuestId = guestByGuestId;
    }
}
