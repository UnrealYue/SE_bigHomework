package com.iL.hotel.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "order", schema = "hotel", catalog = "")
public class OrderEntity {
    private int orderId;
    private Timestamp createTime;
    private Timestamp finishTime;
    private byte stayDays;
    private byte numOfPeople;
    private String notes;
    private byte isReservation;
    private byte isDeleted;
    private byte isFinished;
    private byte isChecked;
    private Byte checkoutMethod;
    private GuestEntity guestByGuestId;
    private Collection<OrderRoomEntity> orderRoomsByOrderId;
    private Collection<ReservationEntity> reservationsByOrderId;

    @Id
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "finish_time", nullable = true)
    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    @Basic
    @Column(name = "stay_days", nullable = false)
    public byte getStayDays() {
        return stayDays;
    }

    public void setStayDays(byte stayDays) {
        this.stayDays = stayDays;
    }

    @Basic
    @Column(name = "num_of_people", nullable = false)
    public byte getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(byte numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    @Basic
    @Column(name = "notes", nullable = true, length = 255)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "is_reservation", nullable = false)
    public byte getIsReservation() {
        return isReservation;
    }

    public void setIsReservation(byte isReservation) {
        this.isReservation = isReservation;
    }

    @Basic
    @Column(name = "is_deleted", nullable = false)
    public byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Basic
    @Column(name = "is_finished", nullable = false)
    public byte getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(byte isFinished) {
        this.isFinished = isFinished;
    }

    @Basic
    @Column(name = "is_checked", nullable = false)
    public byte getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(byte isChecked) {
        this.isChecked = isChecked;
    }

    @Basic
    @Column(name = "checkout_method", nullable = true)
    public Byte getCheckoutMethod() {
        return checkoutMethod;
    }

    public void setCheckoutMethod(Byte checkoutMethod) {
        this.checkoutMethod = checkoutMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (orderId != that.orderId) return false;
        if (stayDays != that.stayDays) return false;
        if (numOfPeople != that.numOfPeople) return false;
        if (isReservation != that.isReservation) return false;
        if (isDeleted != that.isDeleted) return false;
        if (isFinished != that.isFinished) return false;
        if (isChecked != that.isChecked) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (finishTime != null ? !finishTime.equals(that.finishTime) : that.finishTime != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (checkoutMethod != null ? !checkoutMethod.equals(that.checkoutMethod) : that.checkoutMethod != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        result = 31 * result + (int) stayDays;
        result = 31 * result + (int) numOfPeople;
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (int) isReservation;
        result = 31 * result + (int) isDeleted;
        result = 31 * result + (int) isFinished;
        result = 31 * result + (int) isChecked;
        result = 31 * result + (checkoutMethod != null ? checkoutMethod.hashCode() : 0);
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

    @OneToMany(mappedBy = "orderByOrderId")
    public Collection<OrderRoomEntity> getOrderRoomsByOrderId() {
        return orderRoomsByOrderId;
    }

    public void setOrderRoomsByOrderId(Collection<OrderRoomEntity> orderRoomsByOrderId) {
        this.orderRoomsByOrderId = orderRoomsByOrderId;
    }

    @OneToMany(mappedBy = "orderByOrderId")
    public Collection<ReservationEntity> getReservationsByOrderId() {
        return reservationsByOrderId;
    }

    public void setReservationsByOrderId(Collection<ReservationEntity> reservationsByOrderId) {
        this.reservationsByOrderId = reservationsByOrderId;
    }
}
