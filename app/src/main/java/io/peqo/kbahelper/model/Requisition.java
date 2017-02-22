package io.peqo.kbahelper.model;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;

/**
 * Model class for a Requisition.
 * Contains information about bloodwork, assigned User and more.
 */
@Entity
public class Requisition {

    @Id(autoincrement = true)
    private Long id;

    private int reqNum;
    private int runNum;
    private Date testTime;

    @ToOne
    private Requestor requestor;

    @ToOne
    private Patient patient;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1825104607)
    private transient RequisitionDao myDao;

    @Generated(hash = 816114082)
    public Requisition(Long id, int reqNum, int runNum, Date testTime) {
        this.id = id;
        this.reqNum = reqNum;
        this.runNum = runNum;
        this.testTime = testTime;
    }

    @Generated(hash = 1662380300)
    public Requisition() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 2078428731)
    private transient boolean patient__refreshed;

    @Generated(hash = 690195104)
    private transient boolean requestor__refreshed;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1939769779)
    public Patient getPatient() {
        if (patient != null || !patient__refreshed) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PatientDao targetDao = daoSession.getPatientDao();
            targetDao.refresh(patient);
            patient__refreshed = true;
        }
        return patient;
    }

    /** To-one relationship, returned entity is not refreshed and may carry only the PK property. */
    @Generated(hash = 1923605878)
    public Patient peakPatient() {
        return patient;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 194969539)
    public void setPatient(Patient patient) {
        synchronized (this) {
            this.patient = patient;
            patient__refreshed = true;
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

    public int getReqNum() {
        return this.reqNum;
    }

    public void setReqNum(int reqNum) {
        this.reqNum = reqNum;
    }

    public int getRunNum() {
        return this.runNum;
    }

    public void setRunNum(int runNum) {
        this.runNum = runNum;
    }

    public Date getTestTime() {
        return this.testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1454400281)
    public Requestor getRequestor() {
        if (requestor != null || !requestor__refreshed) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RequestorDao targetDao = daoSession.getRequestorDao();
            targetDao.refresh(requestor);
            requestor__refreshed = true;
        }
        return requestor;
    }

    /** To-one relationship, returned entity is not refreshed and may carry only the PK property. */
    @Generated(hash = 1470020448)
    public Requestor peakRequestor() {
        return requestor;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 595836249)
    public void setRequestor(Requestor requestor) {
        synchronized (this) {
            this.requestor = requestor;
            requestor__refreshed = true;
        }
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 771297231)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRequisitionDao() : null;
    }

}
