package io.peqo.kbahelper;

import android.app.Application;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

import java.util.Date;

import io.peqo.kbahelper.model.DaoMaster;
import io.peqo.kbahelper.model.DaoSession;
import io.peqo.kbahelper.model.Patient;
import io.peqo.kbahelper.model.PatientDao;
import io.peqo.kbahelper.model.Requestor;
import io.peqo.kbahelper.model.RequestorDao;
import io.peqo.kbahelper.model.Requisition;
import io.peqo.kbahelper.model.RequisitionDao;
import io.peqo.kbahelper.model.Sample;
import io.peqo.kbahelper.model.SampleDao;

public class MainApplication extends Application {

    private static final String TAG = MainApplication.class.getSimpleName();
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "kbahelper.db");
        Database db = helper.getWritableDb();
        DaoMaster.dropAllTables(db, true);
        DaoMaster.createAllTables(db, true);
        daoSession = new DaoMaster(db).newSession();

        load();

        Log.d(TAG, "Database opened for transaction.");
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    private void load() {
        PatientDao patientDao = daoSession.getPatientDao();
        RequestorDao requestorDao = daoSession.getRequestorDao();
        RequisitionDao requisitionDao = daoSession.getRequisitionDao();
        SampleDao sampleDao = daoSession.getSampleDao();

        Patient patient1 = new Patient();
        patient1.setCustomerNum(11223);
        patient1.setCprNum("010101-0101");
        patient1.setLastName("Jørgensen");
        patient1.setFirstName("Nicklas");

        Patient patient2 = new Patient();
        patient2.setCustomerNum(11223);
        patient2.setCprNum("010101-0101");
        patient2.setLastName("Byrresen");
        patient2.setFirstName("Tobias");

        Requestor requestor = new Requestor();
        requestor.setPostalCode("7700 Thisted");
        requestor.setName("M4(THI)");
        requestor.setDepartment("Medicinsk afd. M4");
        requestor.setCountry("Danmark");
        requestor.setAddress("Højtoftevej 2");

        patientDao.insert(patient1);
        patientDao.insert(patient2);
        requestorDao.insert(requestor);

        Requisition requisition1 = new Requisition();
        requisition1.setPatient(patient1);
        requisition1.setRequestor(requestor);
        requisition1.setReqNum(1015);
        requisition1.setRunNum(4);
        requisition1.setTestTime(new Date());

        Requisition requisition2 = new Requisition();
        requisition2.setPatient(patient2);
        requisition2.setRequestor(requestor);
        requisition2.setReqNum(1021);
        requisition2.setRunNum(6);
        requisition2.setTestTime(new Date());

        requisitionDao.insert(requisition1);
        requisitionDao.insert(requisition2);

        Sample sample1 = new Sample();
        sample1.setName("Glucose");
        sample1.setRequisitionId(requisition1.getId());
        sampleDao.insert(sample1);

        Sample sample2 = new Sample();
        sample2.setName("Natrium");
        sample2.setRequisitionId(requisition1.getId());
        sampleDao.insert(sample2);

        Sample sample3 = new Sample();
        sample3.setName("Potassium");
        sample3.setRequisitionId(requisition1.getId());
        sampleDao.insert(sample3);

        Sample sample4 = new Sample();
        sample4.setName("Sodium");
        sample4.setRequisitionId(requisition2.getId());
        sampleDao.insert(sample4);

        requisition1.getSamples().add(sample1);
        requisition1.getSamples().add(sample2);
        requisition2.getSamples().add(sample3);
        requisition2.getSamples().add(sample4);
    }
}
