public class TreatmentRecord {
    private static int nextTreatmentId = 1;

    private int treatmentId;
    private int patientId;
    private String patientName;
    private String doctorName;
    private String diagnosis;
    private String date;

    public TreatmentRecord(String doctorName, String diagnosis, int patientId, String patientName, String date) {
        this.treatmentId = nextTreatmentId++;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.patientId = patientId;
        this.patientName = patientName;
        this.date = date;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Treatment ID: " + treatmentId +
                " | Patient ID: " + patientId +
                " | Patient Name: " + patientName +
                " | Doctor: " + doctorName +
                " | Diagnosis: " + diagnosis +
                " | Date: " + date;
    }
}
