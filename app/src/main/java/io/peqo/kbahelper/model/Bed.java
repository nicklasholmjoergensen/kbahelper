package io.peqo.kbahelper.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

/**
 * Class representing a bed, which has a many-to-one relationship with a Room,
 * and a one-to-one relationship with a Patient.
 */

@Entity
public class Bed {

    @Id(autoincrement = true)
    private Long id;

    private int bedNumber;

    private Long roomId;

    @ToOne(joinProperty = "roomId")
    private Room room;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 571450465)
    private transient BedDao myDao;

    @Generated(hash = 170076450)
    private transient Long room__resolvedKey;

    @Generated(hash = 474456843)
    public Bed(Long id, int bedNumber, Long roomId) {
        this.id = id;
        this.bedNumber = bedNumber;
        this.roomId = roomId;
    }

    @Generated(hash = 191207028)
    public Bed() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return this.roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 422076784)
    public Room getRoom() {
        Long __key = this.roomId;
        if (room__resolvedKey == null || !room__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RoomDao targetDao = daoSession.getRoomDao();
            Room roomNew = targetDao.load(__key);
            synchronized (this) {
                room = roomNew;
                room__resolvedKey = __key;
            }
        }
        return room;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1580335853)
    public void setRoom(Room room) {
        synchronized (this) {
            this.room = room;
            roomId = room == null ? null : room.getId();
            room__resolvedKey = roomId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 889258170)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBedDao() : null;
    }

}
