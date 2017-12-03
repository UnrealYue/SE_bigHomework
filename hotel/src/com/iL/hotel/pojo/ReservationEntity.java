package com.iL.hotel.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reservation", schema = "hotel", catalog = "")
public class ReservationEntity {
    private Timestamp preCheckinTime;
    private byte preStayDays;
    private byte guestNum;
    private int id;
    private OrderEntity orderByOrderId;

    @Basic
    @Column(name = "pre_checkin_time", nullable = false)
    public Timestamp getPreCheckinTime() {
        return preCheckinTime;
    }

    public void setPreCheckinTime(Timestamp preCheckinTime) {
        this.preCheckinTime = preCheckinTime;
    }

    @Basic
    @Column(name = "pre_stay_days", nullable = false)
    public byte getPreStayDays() {
        return preStayDays;
    }

    public void setPreStayDays(byte preStayDays) {
        this.preStayDays = preStayDays;
    }

    @Basic
    @Column(name = "guest_num", nullable = false)
    public byte getGuestNum() {
        return guestNum;
    }

    public void setGuestNum(byte guestNum) {
        this.guestNum = guestNum;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationEntity that = (ReservationEntity) o;

        if (preStayDays != that.preStayDays) return false;
        if (guestNum != that.guestNum) return false;
        if (id != that.id) return false;
        if (preCheckinTime != null ? !preCheckinTime.equals(that.preCheckinTime) : that.preCheckinTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = preCheckinTime != null ? preCheckinTime.hashCode() : 0;
        result = 31 * result + (int) preStayDays;
        result = 31 * result + (int) guestNum;
        result = 31 * result + id;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    public OrderEntity getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(OrderEntity orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }
}
