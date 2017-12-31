package com.iL.hotel.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

public class CheckedOrderInfoForShow {


    public RoomEntity getRoomEntity() {
        return roomEntity;
    }

    public void setRoomEntity(RoomEntity roomEntity) {
        this.roomEntity = roomEntity;
    }

    public CheckedOrderInfoForShow(RoomEntity roomEntity, OrderRoomEntity orderRoomEntity, OrderEntity orderEntity, GuestEntity guestEntity) {
        this.roomEntity = roomEntity;
        this.roomEntity.setOrderRoomsByRoomId(null);
        this.orderRoomEntity = orderRoomEntity;
        this.orderRoomEntity.setOrderByOrderId(null);
        this.orderRoomEntity.setRoomByRoomId(null);
        this.orderEntity = orderEntity;
        this.orderEntity.setGuestByGuestId(null);
        this.orderEntity.setOrderRoomsByOrderId(null);
        this.orderEntity.setReservationsByOrderId(null);
        this.guestEntity = guestEntity;
        this.guestEntity.setOrdersByGuestId(null);
        this.guestEntity.setVipsByGuestId(null);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        checkInTime=sdf.format(orderRoomEntity.getCheckinTime()) ;
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(orderRoomEntity.getCheckinTime());
        calendar.add(Calendar.DAY_OF_MONTH, this.orderEntity.getStayDays());

       // if(orderRoomEntity.getCheckoutTime()!=null)
        checkOutTime=sdf.format(calendar.getTimeInMillis());
        if(guestEntity.getIsVip()==1){
            GuestType="VIP客户";
        }
        else {
            GuestType="普通客户";
        }
        if(roomEntity.getRoomState()==0){
            RoomState="空闲";
        }
        else if(roomEntity.getRoomState()==1){
            RoomState="已入住";
        }
        else {
            RoomState="维修中";
        }
    }

    /*public CheckedOrderInfoForShow(int roomId, Date checkinTime, Date checkoutTime, String guestName, String idCard, String phoneNum, byte isVip, short roomPrice, byte roomState) {
                this.roomId = roomId;
                this.checkinTime = new java.sql.Date(checkinTime.getTime());
                this.checkoutTime = new java.sql.Date(checkoutTime.getTime());
                this.guestName = guestName;
                this.idCard = idCard;
                this.phoneNum = phoneNum;
                this.isVip = isVip;
                this.roomPrice = roomPrice;
                this.roomState = roomState;
            }

            public int roomId;
            public java.sql.Date checkinTime;
            public java.sql.Date checkoutTime;
            public String guestName;
            public String idCard;
            public String phoneNum;
            public byte isVip;
            public short roomPrice;
            public byte roomState;
        */
    public RoomEntity roomEntity;
    public OrderRoomEntity orderRoomEntity;
    public OrderEntity orderEntity;
    public GuestEntity guestEntity;
    public String checkInTime;
    public String checkOutTime;
    public String GuestType;
    public String RoomState;




}
