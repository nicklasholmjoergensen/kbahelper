package io.peqo.kbahelper.model;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;

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

    private Long requestorId;

    @ToOne(joinProperty = "requestorId")
    private Requestor requestor;

    private Long patientId;

    @ToOne(joinProperty = "patientId")
    private Patient patient;

    @ToMany(referencedJoinProperty = "requisitionId")
    private List<Sample> samples;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1825104607)
    private transient RequisitionDao myDao;

    @Generated(hash = 1992092101)
    public Requisition(Long id, int reqNum, int runNum, Date testTime, Long requestorId,
            Long patientId) {
        this.id = id;
        this.reqNum = reqNum;
        this.runNum = runNum;
        this.testTime = testTime;
        this.requestorId = requestorId;
        this.patientId = patientId;
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

    @Generated(hash = 207025544)
    private transient Long requestor__resolvedKey;

    @Generated(hash = 391381774)
    private transient Long patient__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1923256233)
    public Patient getPatient() {
        Long __key = this.patientId;
        if (patient__resolvedKey == null || !patient__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PatientDao targetDao = daoSession.getPatientDao();
            Patient patientNew = targetDao.load(__key);
            synchronized (this) {
                patient = patientNew;
                patient__resolvedKey = __key;
            }
        }
        return patient;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 459152036)
    public void setPatient(Patient patient) {
        synchronized (this) {
            this.patient = patient;
            patientId = patient == null ? null : patient.getId();
            patient__resolvedKey = patientId;
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

    @Override
    public String toString() {
        return "Requisition{" +
                "id=" + id +
                ", reqNum=" + reqNum +
                ", runNum=" + runNum +
                ", testTime=" + testTime +
                ", requestor=" + getRequestorId() +
                ", patient=" + getPatientId() + '\'' +
                '}';
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1934454346)
    public Requestor getRequestor() {
        Long __key = this.requestorId;
        if (requestor__resolvedKey == null || !requestor__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RequestorDao targetDao = daoSession.getRequestorDao();
            Requestor requestorNew = targetDao.load(__key);
            synchronized (this) {
                requestor = requestorNew;
                requestor__resolvedKey = __key;
            }
        }
        return requestor;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 143239411)
    public void setRequestor(Requestor requestor) {
        synchronized (this) {
            this.requestor = requestor;
            requestorId = requestor == null ? null : requestor.getId();
            requestor__resolvedKey = requestorId;
        }
    }

    public Long getRequestorId() {
        return this.requestorId;
    }

    public void setRequestorId(Long requestorId) {
        this.requestorId = requestorId;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 931775220)
    public List<Sample> getSamples() {
        if (samples == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SampleDao targetDao = daoSession.getSampleDao();
            List<Sample> samplesNew = targetDao._queryRequisition_Samples(id);
            synchronized (this) {
                if (samples == null) {
                    samples = samplesNew;
                }
            }
        }
        return samples;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2140860406)
    public synchronized void resetSamples() {
        samples = null;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 771297231)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRequisitionDao() : null;
    }
}
