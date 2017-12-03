package com.iL.hotel.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "order_room", schema = "hotel", catalog = "")
public class OrderRoomEntity {
    private int orderRoomId;
    private Timestamp checkinTime;
    private Timestamp checkoutTime;
    private String notes;
    private OrderEntity orderByOrderId;
    private RoomEntity roomByRoomId;

    @Id
    @Column(name = "order_room_id", nullable = false)
    public int getOrderRoomId() {
        return orderRoomId;
    }

    public void setOrderRoomId(int orderRoomId) {
        this.orderRoomId = orderRoomId;
    }

    @Basic
    @Column(name = "checkin_time", nullable = false)
    public Timestamp getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Timestamp checkinTime) {
        this.checkinTime = checkinTime;
    }

    @Basic
    @Column(name = "checkout_time", nullable = true)
    public Timestamp getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(Timestamp checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    @Basic
    @Column(name = "notes", nullable = true, length = 255)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderRoomEntity that = (OrderRoomEntity) o;

        if (orderRoomId != that.orderRoomId) return false;
        if (checkinTime != null ? !checkinTime.equals(that.checkinTime) : that.checkinTime != null) return false;
        if (checkoutTime != null ? !checkoutTime.equals(that.checkoutTime) : that.checkoutTime != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderRoomId;
        result = 31 * result + (checkinTime != null ? checkinTime.hashCode() : 0);
        result = 31 * result + (checkoutTime != null ? checkoutTime.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id", nullable = false)
    public RoomEntity getRoomByRoomId() {
        return roomByRoomId;
    }

    public void setRoomByRoomId(RoomEntity roomByRoomId) {
        this.roomByRoomId = roomByRoomId;
    }
}
