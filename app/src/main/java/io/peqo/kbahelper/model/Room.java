package io.peqo.kbahelper.model;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;

/**
 * Class modelling a room with x-number of patients.
 */

@Entity
public class Room {

    @Id(autoincrement = true)
    private Long id;
    private int roomNumber;

    private Long departmentId;

    @ToOne(joinProperty = "departmentId")
    private Department department;

    @ToMany(referencedJoinProperty = "roomId")
    private List<Bed> beds;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 740313876)
    private transient RoomDao myDao;
    @Generated(hash = 340684718)
    private transient Long department__resolvedKey;

    @Generated(hash = 1538932540)
    public Room(Long id, int roomNumber, Long departmentId) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.departmentId = departmentId;
    }

    @Generated(hash = 703125385)
    public Room() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 321259664)
    public List<Bed> getBeds() {
        if (beds == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BedDao targetDao = daoSession.getBedDao();
            List<Bed> bedsNew = targetDao._queryRoom_Beds(id);
            synchronized (this) {
                if (beds == null) {
                    beds = bedsNew;
                }
            }
        }
        return beds;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2010115639)
    public synchronized void resetBeds() {
        beds = null;
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

    public Long getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1998261213)
    public Department getDepartment() {
        Long __key = this.departmentId;
        if (department__resolvedKey == null || !department__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DepartmentDao targetDao = daoSession.getDepartmentDao();
            Department departmentNew = targetDao.load(__key);
            synchronized (this) {
                department = departmentNew;
                department__resolvedKey = __key;
            }
        }
        return department;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2010443684)
    public void setDepartment(Department department) {
        synchronized (this) {
            this.department = department;
            departmentId = department == null ? null : department.getId();
            department__resolvedKey = departmentId;
        }
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1185512297)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRoomDao() : null;
    }

}
