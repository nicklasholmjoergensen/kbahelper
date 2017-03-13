package io.peqo.kbahelper.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Testing class for Patient Model
 */

public class PatientTest {

    @Test
    public void createNewPatient() {
        Patient patient = new Patient.Builder()
                .setId(1L)
                .setCprNum("041189-2837")
                .setFirstName("Nicklas")
                .setLastName("Jørgensen")
                .setRegistered(false)
                .build();
        assertThat(patient.id, is(equalTo(1L)));
    }
}
