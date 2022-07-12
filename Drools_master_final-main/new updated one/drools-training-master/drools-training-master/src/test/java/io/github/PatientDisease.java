package io.github;

import model.Patient;
import model.PatientDisease;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class PatientDiseaseTest {

    @Test
    public void allTestCases() {
        List<Patient> patientList = new ArrayList<>();
        patientList.add(new Patient(1, "R13.1", "Active", LocalDate.of(2022, 06, 30), LocalDate.of(1990, 06, 30)));
        patientList.add(new Patient(1, "K21.0", "Active", LocalDate.of(2022, 06, 30), LocalDate.of(2000, 9, 04)));
        List<String> diseaseName = new ArrayList<>();
        diseaseName.add("GERD");
        diseaseName.add("Dysphagia");

        for (int i = 0; i < patientList.size(); i++) {
            testForIcd(patientList.get(i), diseaseName.get(i));
        }
    }

    @Test
    public static void testForIcd(Patient p, String disease) {
        KieSession ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("diseaserules");
        PatientDisease patientDisease = new PatientDisease();
        ksession.insert(p);
        ksession.insert(patientDisease);
        ksession.fireAllRules();
        ksession.dispose();
        assert (patientDisease.getMaplist().get(p).contains(disease));
    }



}
