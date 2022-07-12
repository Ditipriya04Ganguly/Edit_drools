package DiseaseTest;


import model.Patient;
import model.PatientDisease;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class DTest {
    public static void main(String[] args){
        try {
            KieSession ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("diseaserules");
            Set<String> ICD10= new HashSet<>();


//
//            Set<String> icdcode= new HashSet<String>();
//            icdcode.add("R13.1");
//            icdcode.add("R63.4");

            Patient p= new Patient(1,"R13.1", "Active", LocalDate.of(2022,05,23),LocalDate.of(1990,01,13));
            Patient p2= new Patient(2,"R55", "Active", LocalDate.of(2022,02,13),LocalDate.of(1985,10,23));
            Patient p3= new Patient(3,"E64.0", "Active", LocalDate.of(2022,06,01),LocalDate.of(1999,12,23));
            Patient pp3= new Patient(3,"D50.8", "Active", LocalDate.of(2022,04,30),LocalDate.of(1999,12,23));
            Patient p4= new Patient(4,"R68.81", "Inactive", LocalDate.of(2022,04,10),LocalDate.of(1993,9,04));
            Patient p5= new Patient(5,"K21.0", "Active", LocalDate.of(2022,05,29),LocalDate.of(1970,8,12));
            Patient pp5= new Patient(5,"K40", "Active", LocalDate.of(2022,05,29),LocalDate.of(1970,8,12));
            Patient p6= new Patient(6,"K21.9", "Inactive", LocalDate.of(2022,06,15),LocalDate.of(1969,6,25));
            Patient pp6= new Patient(6,"E64.0", "Active", LocalDate.of(2022,04,10),LocalDate.of(1969,6,25));
            Patient ppp6= new Patient(6,"Z72.0", "Active", LocalDate.of(2022,03,03),LocalDate.of(1969,6,25));
            Patient pppp6= new Patient(6,"K40", "Active", LocalDate.of(2022,07,01),LocalDate.of(1969,6,25));
            ppp6.setSmokingStatus("Positive");
            Patient pp7= new Patient(7,"E66", "Active", LocalDate.of(2022,05,05),LocalDate.of(1969,6,01));

            //Patient p3= new Patient(1,"R13.1", "Active", LocalDate.of(2022,06,30),LocalDate.of(1960,06,16));






            //pp10.setSmokingStatus("Positive");




            PatientDisease patientDisease= new PatientDisease();


            ksession.insert(p);
            ksession.insert(p2);
            ksession.insert(p3);
            ksession.insert(pp3);
            ksession.insert(p4);
            ksession.insert(p5);
            ksession.insert(pp5);
            ksession.insert(p6);
            ksession.insert(pp6);
            ksession.insert(ppp6);
            ksession.insert(pppp6);
            ksession.insert(pp7);
            //ksession.insert(pp2);
            ksession.insert(LocalDate.now());
            ksession.insert(patientDisease);
            ksession.fireAllRules();
            ksession.dispose();
            //System.out.println(p);

            for(Patient p1: patientDisease.getMaplist().keySet())
            {
                System.out.print("patient with id "+p1.getId()+" has disease ");
                System.out.println(patientDisease.getMaplist().get(p1));
                System.out.println("Prefill value: "+ p1.getPreFill());
                System.out.println("Multiple value: "+p1.getMultiple());
                System.out.println("Multiple Risk prefill: "+p1.getMultipleG());
                System.out.println("Risk Factor: "+p1.getRisk());
            }



        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}

