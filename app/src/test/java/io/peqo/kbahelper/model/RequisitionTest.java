package io.peqo.kbahelper.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class RequisitionTest {

    Requisition requisition;

    @Before
    public void setup() {
        requisition = new Requisition.Builder()
                .setId(1L)
                .setOrderDate(new Date())
                .setReqNum(10104)
                .setRunNum(1223)
                .setPatientId(1L)
                .setStatus(0)
                .setRequestorId(1L)
                .build();
    }

    @Test
    public void createNewRequisition() {
        assertThat(requisition.id, is(equalTo(1L)));
        assertThat(requisition.reqNum, is(equalTo(10104)));
    }

    @Test
    public void createNewRequisitionFromExisting() {
        Requisition req = new Requisition.Builder()
                .basedOn(requisition)
                .setStatus(1)
                .setFullfilledDate(new Date())
                .build();
        assertThat(req.status, is(not(requisition.status)));
        assertThat(req.id, is(equalTo(requisition.id)));
        assertThat(req.reqNum, is(equalTo(requisition.reqNum)));
    }
}
