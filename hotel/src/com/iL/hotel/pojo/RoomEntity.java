package com.iL.hotel.pojo;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "room", schema = "hotel", catalog = "")
public class RoomEntity {
    private int roomId;
    private byte rommType;
    private short roomPrice;
    private byte roomState;
    private String roomStyle;
    private Collection<OrderRoomEntity> orderRoomsByRoomId;

    @Id
    @Column(name = "room_id", nullable = false)
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "romm_type", nullable = false)
    public byte getRommType() {
        return rommType;
    }

    public void setRommType(byte rommType) {
        this.rommType = rommType;
    }

    @Basic
    @Column(name = "room_price", nullable = false)
    public short getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(short roomPrice) {
        this.roomPrice = roomPrice;
    }

    @Basic
    @Column(name = "room_state", nullable = false)
    public byte getRoomState() {
        return roomState;
    }

    public void setRoomState(byte roomState) {
        this.roomState = roomState;
    }

    @Basic
    @Column(name = "room_style", nullable = true, length = 255)
    public String getRoomStyle() {
        return roomStyle;
    }

    public void setRoomStyle(String roomStyle) {
        this.roomStyle = roomStyle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomEntity that = (RoomEntity) o;

        if (roomId != that.roomId) return false;
        if (rommType != that.rommType) return false;
        if (roomPrice != that.roomPrice) return false;
        if (roomState != that.roomState) return false;
        if (roomStyle != null ? !roomStyle.equals(that.roomStyle) : that.roomStyle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomId;
        result = 31 * result + (int) rommType;
        result = 31 * result + (int) roomPrice;
        result = 31 * result + (int) roomState;
        result = 31 * result + (roomStyle != null ? roomStyle.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "roomByRoomId")
    public Collection<OrderRoomEntity> getOrderRoomsByRoomId() {
        return orderRoomsByRoomId;
    }

    public void setOrderRoomsByRoomId(Collection<OrderRoomEntity> orderRoomsByRoomId) {
        this.orderRoomsByRoomId = orderRoomsByRoomId;
    }
}
