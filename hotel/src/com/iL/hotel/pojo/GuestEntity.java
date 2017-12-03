package com.iL.hotel.pojo;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "guest", schema = "hotel", catalog = "")
public class GuestEntity {
    private int guestId;
    private String guestName;
    private Integer gendar;
    private String idCard;
    private String phoneNum;
    private byte isVip;
    private Collection<OrderEntity> ordersByGuestId;
    private Collection<VipEntity> vipsByGuestId;

    @Id
    @Column(name = "guest_id", nullable = false)
    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    @Basic
    @Column(name = "guest_name", nullable = true, length = 255)
    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    @Basic
    @Column(name = "gendar", nullable = true)
    public Integer getGendar() {
        return gendar;
    }

    public void setGendar(Integer gendar) {
        this.gendar = gendar;
    }

    @Basic
    @Column(name = "id_card", nullable = true, length = 255)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "phone_num", nullable = true, length = 20)
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Basic
    @Column(name = "is_vip", nullable = false)
    public byte getIsVip() {
        return isVip;
    }

    public void setIsVip(byte isVip) {
        this.isVip = isVip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GuestEntity that = (GuestEntity) o;

        if (guestId != that.guestId) return false;
        if (isVip != that.isVip) return false;
        if (guestName != null ? !guestName.equals(that.guestName) : that.guestName != null) return false;
        if (gendar != null ? !gendar.equals(that.gendar) : that.gendar != null) return false;
        if (idCard != null ? !idCard.equals(that.idCard) : that.idCard != null) return false;
        if (phoneNum != null ? !phoneNum.equals(that.phoneNum) : that.phoneNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = guestId;
        result = 31 * result + (guestName != null ? guestName.hashCode() : 0);
        result = 31 * result + (gendar != null ? gendar.hashCode() : 0);
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (phoneNum != null ? phoneNum.hashCode() : 0);
        result = 31 * result + (int) isVip;
        return result;
    }

    @OneToMany(mappedBy = "guestByGuestId")
    public Collection<OrderEntity> getOrdersByGuestId() {
        return ordersByGuestId;
    }

    public void setOrdersByGuestId(Collection<OrderEntity> ordersByGuestId) {
        this.ordersByGuestId = ordersByGuestId;
    }

    @OneToMany(mappedBy = "guestByGuestId")
    public Collection<VipEntity> getVipsByGuestId() {
        return vipsByGuestId;
    }

    public void setVipsByGuestId(Collection<VipEntity> vipsByGuestId) {
        this.vipsByGuestId = vipsByGuestId;
    }
}
