package io.peqo.kbahelper.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

/**
 * Class for representing a patient.
 */

@Entity
public class Patient {

    @Id(autoincrement = true)
    private Long id;

    private Long bedId;

    @ToOne(joinProperty = "bedId")
    private Bed bed;

    private String firstName;
    private String lastName;
    private int customerNum;
    private String cprNum;
    private boolean registered;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 913916263)
    private transient PatientDao myDao;

    @Generated(hash = 2068237315)
    private transient Long bed__resolvedKey;

    public Patient() {}

    @Generated(hash = 685621546)
    public Patient(Long id, Long bedId, String firstName, String lastName,
            int customerNum, String cprNum, boolean registered) {
        this.id = id;
        this.bedId = bedId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNum = customerNum;
        this.cprNum = cprNum;
        this.registered = registered;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(int customerNum) {
        this.customerNum = customerNum;
    }

    public String getCprNum() {
        return cprNum;
    }

    public void setCprNum(String cprNum) {
        this.cprNum = cprNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", customerNum=" + customerNum +
                ", cprNum='" + cprNum + '\'' +
                '}';
    }

    public boolean getRegistered() {
        return this.registered;
    }

    public Long getBedId() {
        return this.bedId;
    }

    public void setBedId(Long bedId) {
        this.bedId = bedId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1749950714)
    public Bed getBed() {
        Long __key = this.bedId;
        if (bed__resolvedKey == null || !bed__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BedDao targetDao = daoSession.getBedDao();
            Bed bedNew = targetDao.load(__key);
            synchronized (this) {
                bed = bedNew;
                bed__resolvedKey = __key;
            }
        }
        return bed;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 507810379)
    public void setBed(Bed bed) {
        synchronized (this) {
            this.bed = bed;
            bedId = bed == null ? null : bed.getId();
            bed__resolvedKey = bedId;
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
    @Generated(hash = 244362399)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPatientDao() : null;
    }
}
