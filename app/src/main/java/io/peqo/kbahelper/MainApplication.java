package io.peqo.kbahelper;

import android.app.Application;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

import java.util.Date;

import io.peqo.kbahelper.model.Bed;
import io.peqo.kbahelper.model.BedDao;
import io.peqo.kbahelper.model.DaoMaster;
import io.peqo.kbahelper.model.DaoSession;
import io.peqo.kbahelper.model.Department;
import io.peqo.kbahelper.model.DepartmentDao;
import io.peqo.kbahelper.model.Patient;
import io.peqo.kbahelper.model.PatientDao;
import io.peqo.kbahelper.model.Requestor;
import io.peqo.kbahelper.model.RequestorDao;
import io.peqo.kbahelper.model.Requisition;
import io.peqo.kbahelper.model.RequisitionDao;
import io.peqo.kbahelper.model.Room;
import io.peqo.kbahelper.model.RoomDao;
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
        RoomDao roomDao = daoSession.getRoomDao();
        BedDao bedDao = daoSession.getBedDao();
        DepartmentDao departmentDao = daoSession.getDepartmentDao();

        Department dept = new Department();
        dept.setName("KBA.Thi(THI)");

        departmentDao.insert(dept);

        Room room = new Room();
        room.setRoomNumber(1);
        room.setDepartmentId(dept.getId());
        roomDao.insert(room);

        dept.getRooms().add(room);

        Bed bed1 = new Bed();
        bed1.setBedNumber(1);
        bed1.setRoomId(room.getId());
        bedDao.insert(bed1);

        Bed bed2 = new Bed();
        bed2.setBedNumber(2);
        bed2.setRoomId(room.getId());
        bedDao.insert(bed2);

        Bed bed3 = new Bed();
        bed3.setBedNumber(3);
        bed3.setRoomId(room.getId());
        bedDao.insert(bed3);

        Bed bed4 = new Bed();
        bed4.setBedNumber(4);
        bed4.setRoomId(room.getId());
        bedDao.insert(bed4);

        room.getBeds().add(bed1);
        room.getBeds().add(bed2);
        room.getBeds().add(bed3);
        room.getBeds().add(bed4);

        Patient patient1 = new Patient();
        patient1.setCustomerNum(11223);
        patient1.setCprNum("010101-0101");
        patient1.setLastName("Jørgensen");
        patient1.setFirstName("Nicklas");

        Patient patient2 = new Patient();
        patient2.setCustomerNum(11222);
        patient2.setCprNum("010101-0101");
        patient2.setLastName("Byrresen");
        patient2.setFirstName("Tobias");

        Patient patient3 = new Patient();
        patient3.setCustomerNum(15223);
        patient3.setCprNum("220171-4433");
        patient3.setLastName("Pedersen");
        patient3.setFirstName("Helle");

        Patient patient4 = new Patient();
        patient4.setCustomerNum(15223);
        patient4.setCprNum("121088-2313");
        patient4.setLastName("Hansen");
        patient4.setFirstName("Torben");

        patient1.setBedId(bed1.getId());
        patient2.setBedId(bed2.getId());
        patient3.setBedId(bed3.getId());
        patient4.setBedId(bed4.getId());

        patientDao.insert(patient1);
        patientDao.insert(patient2);
        patientDao.insert(patient3);
        patientDao.insert(patient4);

        Requestor requestor = new Requestor();
        requestor.setPostalCode("7700 Thisted");
        requestor.setName("M4(THI)");
        requestor.setDepartment("Medicinsk afd. M4");
        requestor.setCountry("Danmark");
        requestor.setAddress("Højtoftevej 2");

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

        Requisition requisition3 = new Requisition();
        requisition3.setPatient(patient3);
        requisition3.setRequestor(requestor);
        requisition3.setReqNum(1022);
        requisition3.setRunNum(7);
        requisition3.setTestTime(new Date());

        Requisition requisition4 = new Requisition();
        requisition4.setPatient(patient4);
        requisition4.setRequestor(requestor);
        requisition4.setReqNum(1023);
        requisition4.setRunNum(9);
        requisition4.setTestTime(new Date());

        requisitionDao.insert(requisition1);
        requisitionDao.insert(requisition2);
        requisitionDao.insert(requisition3);
        requisitionDao.insert(requisition4);

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

        Sample sample5 = new Sample();
        sample5.setName("Natrium");
        sample5.setRequisitionId(requisition3.getId());
        sampleDao.insert(sample5);

        Sample sample6 = new Sample();
        sample6.setName("Sodium");
        sample6.setRequisitionId(requisition3.getId());
        sampleDao.insert(sample6);

        Sample sample7 = new Sample();
        sample7.setName("Glucose");
        sample7.setRequisitionId(requisition4.getId());
        sampleDao.insert(sample7);

        Sample sample8 = new Sample();
        sample8.setName("Ion");
        sample8.setRequisitionId(requisition4.getId());
        sampleDao.insert(sample8);

        Sample sample9 = new Sample();
        sample9.setName("Sodium");
        sample9.setRequisitionId(requisition4.getId());
        sampleDao.insert(sample9);

        requisition1.getSamples().add(sample1);
        requisition1.getSamples().add(sample2);
        requisition1.getSamples().add(sample3);
        requisition2.getSamples().add(sample4);
        requisition3.getSamples().add(sample5);
        requisition3.getSamples().add(sample6);
        requisition4.getSamples().add(sample7);
        requisition4.getSamples().add(sample8);
        requisition4.getSamples().add(sample9);
    }
}
