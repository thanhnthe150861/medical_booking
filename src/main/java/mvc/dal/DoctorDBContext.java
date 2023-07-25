package mvc.dal;

import mvc.model.*;

import javax.print.Doc;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDBContext extends DBContext {
    private static PreparedStatement stm = null;
    private static ResultSet rs = null;

    public Doctor getDoctor(Account account) {
        try {
            String sql = "SELECT d.id, d.username, d.url, d.name, d.gender, d.dob, d.specialty, rd.id AS rank_id, rd.name AS 'rank'\n" +
                    "FROM doctor d\n" +
                    "LEFT JOIN rank_doctor rd ON rd.id = d.rank_id\n" +
                    "WHERE d.username = ?;";
            stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            rs = stm.executeQuery();
            if (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setUserName(rs.getString("username"));
                doctor.setUrl(rs.getString("url"));
                doctor.setName(rs.getString("name"));
                doctor.setGender(rs.getString("gender"));
                doctor.setDob(rs.getDate("dob"));
                doctor.setSpecialty(rs.getString("specialty"));
                doctor.setRankId(rs.getInt("rank_id"));
                Rank rank = new Rank();
                rank.setId(rs.getInt("rank_id"));
                rank.setName(rs.getString("rank"));
                doctor.setRanks(rank);
                return doctor;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Patient getPatientByDoctor(String id) {
        try {
            String sql = "SELECT patient.id, patient.username, patient.url, patient.name, patient.gender, patient.dob, patient.rank_id, rank_patient.name AS rank_name\n" +
                    "FROM patient\n" +
                    "LEFT JOIN rank_patient ON patient.rank_id = rank_patient.id\n" +
                    "WHERE patient.id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(id));
            rs = stm.executeQuery();
            if (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setUserName(rs.getString("username"));
                patient.setUrl(rs.getString("url"));
                patient.setName(rs.getString("name"));
                patient.setGender(rs.getString("gender"));
                patient.setDob(rs.getDate("dob"));
                Rank rank = new Rank();
                rank.setId(rs.getInt("rank_id"));
                rank.setName(rs.getString("rank_name"));
                patient.setRanks(rank);
                return patient;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<MedicalRecord> getInforMyPatients(Doctor doctor, String id) {
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        try {
            String sql = "SELECT booking.id, booking.doctor_id, booking.patient_id, booking.slot_id, booking.booking_reason, booking.date, booking.status,  \n" +
                    "       doctor.url AS doctor_url, doctor.name AS doctor_name, doctor.gender AS doctor_gender,   \n" +
                    "       doctor.dob AS doctor_dob, doctor.specialty, doctor.rank_id AS doctor_rank_id,  \n" +
                    "       patient.url AS patient_url, patient.name AS patient_name, patient.gender AS patient_gender,  \n" +
                    "       patient.dob AS patient_dob, patient.rank_id AS patient_rank_id,  \n" +
                    "       slot.name AS slot_name,  \n" +
                    "       medical_record.id AS medical_record_id, medical_record.diagnosis, medical_record.prescription,  \n" +
                    "       bill.id AS bill_id, bill.pricePrescription, bill.priceMedical, bill.totalPrice, bill.payment_status  \n" +
                    "FROM booking  \n" +
                    "LEFT JOIN doctor ON booking.doctor_id = doctor.id  \n" +
                    "LEFT JOIN patient ON booking.patient_id = patient.id  \n" +
                    "LEFT JOIN slot ON booking.slot_id = slot.id  \n" +
                    "LEFT JOIN medical_record ON booking.id = medical_record.booking_id  \n" +
                    "LEFT JOIN bill ON medical_record.id = bill.medical_record_id  \n" +
                    "WHERE doctor.id = ? AND patient.id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, doctor.getId());
            stm.setInt(2, Integer.parseInt(id));
            rs = stm.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setSlot_id(rs.getInt("slot_id"));
                booking.setBooking_reason(rs.getString("booking_reason"));
                booking.setDate(rs.getDate("date"));
                booking.setStatus(rs.getString("status"));
                Doctor doctors = new Doctor();
                doctors.setId(rs.getInt("doctor_id"));
                doctors.setUrl(rs.getString("doctor_url"));
                doctors.setName(rs.getString("doctor_name"));
                doctors.setDob(rs.getDate("doctor_dob"));
                doctors.setGender(rs.getString("doctor_gender"));
                doctors.setSpecialty(rs.getString("specialty"));
                booking.setDoctor(doctors);
                Patient patient = new Patient();
                patient.setId(rs.getInt("patient_id"));
                patient.setUrl(rs.getString("patient_url"));
                patient.setName(rs.getString("patient_name"));
                patient.setDob(rs.getDate("patient_dob"));
                patient.setGender(rs.getString("patient_gender"));
                booking.setPatient(patient);
                Slot slot = new Slot();
                slot.setId(rs.getInt("slot_id"));
                slot.setName(rs.getString("slot_name"));
                booking.setSlots(slot);
                Bill bill = new Bill();
                bill.setId(rs.getInt("bill_id"));
                bill.setTotalPrice(rs.getFloat("totalPrice"));
                bill.setPriceMedical(rs.getFloat("priceMedical"));
                bill.setPricePrescription(rs.getFloat("pricePrescription"));
                bill.setPayment_status(rs.getString("payment_status"));
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setId(rs.getInt("medical_record_id"));
                medicalRecord.setDiagnosis(rs.getString("diagnosis"));
                medicalRecord.setPrescription(rs.getString("prescription"));
                medicalRecord.setBooking(booking);
                medicalRecord.setBill(bill);
                medicalRecordList.add(medicalRecord);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medicalRecordList;
    }

    public List<Booking> getBooking(Doctor doctor, String status) {
        List<Booking> bookingList = new ArrayList<>();
        try {
            String sql = "SELECT b.id AS booking_id, b.doctor_id, b.patient_id, b.slot_id, b.booking_reason, b.date, b.status, c.name AS patient_name, c.url ,c.gender, c.dob, s.name AS slot_name\n" +
                    "FROM booking b\n" +
                    "LEFT JOIN patient c ON c.id = b.patient_id\n" +
                    "LEFT JOIN slot s ON s.id = b.slot_id\n" +
                    "WHERE b.status = ? and b.doctor_id =  ?;";
            stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setInt(2, doctor.getId());
            rs = stm.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("booking_id"));
                booking.setBooking_reason(rs.getString("booking_reason"));
                booking.setDate(rs.getDate("date"));
                booking.setStatus(rs.getString("status"));
                Patient patient = new Patient();
                patient.setId(rs.getInt("patient_id"));
                patient.setName(rs.getString("patient_name"));
                patient.setUrl(rs.getString("url"));
                patient.setDob(rs.getDate("dob"));
                patient.setGender(rs.getString("gender"));
                booking.setPatient(patient);
                Slot slot = new Slot();
                slot.setId(rs.getInt("slot_id"));
                slot.setName(rs.getString("slot_name"));
                booking.setSlots(slot);
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookingList;
    }

    public void updateBookingStatus(String id, String status) {
        try {
            String sql = "UPDATE booking SET status = ? WHERE id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setInt(2, Integer.parseInt(id));
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDoctor(Doctor doctor) {
        try {
            // Update the account's information
            String accountSql = "UPDATE account SET password = ?, email = ?, phone = ? WHERE username = ?";
            stm = connection.prepareStatement(accountSql);
            stm.setString(1, doctor.getAccount().getPassword());
            stm.setString(2, doctor.getAccount().getEmail());
            stm.setString(3, doctor.getAccount().getPhone());
            stm.setString(4, doctor.getAccount().getUsername());
            stm.executeUpdate();
            // Update the doctor's information
            String doctorSql = "UPDATE doctor SET url = ?, name = ?, gender = ?, dob = ?, specialty = ?, rank_id = ? WHERE username = ?";
            stm = connection.prepareStatement(doctorSql);
            stm.setString(1, doctor.getUrl());
            stm.setString(2, doctor.getName());
            stm.setString(3, doctor.getGender());
            stm.setDate(4, doctor.getDob());
            stm.setString(5, doctor.getSpecialty());
            stm.setInt(6, doctor.getRankId());
            stm.setString(7, doctor.getUserName());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Patient> getMyPatient(Doctor doctor, String status) {
        List<Patient> patientList = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT p.id, p.url, p.name, p.gender, p.dob, rp.name AS rank_name\n" +
                    "FROM patient p\n" +
                    "LEFT JOIN rank_patient rp ON p.rank_id = rp.id\n" +
                    "INNER JOIN booking b ON p.id = b.patient_id\n" +
                    "WHERE b.status = ? AND b.doctor_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setInt(2, doctor.getId());
            rs = stm.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setName(rs.getString("name"));
                patient.setUrl(rs.getString("url"));
                patient.setDob(rs.getDate("dob"));
                patient.setGender(rs.getString("gender"));
                Rank rank = new Rank();
                rank.setName(rs.getString("rank_name"));
                patient.setRanks(rank);
                patientList.add(patient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patientList;
    }

    public List<Slot> checkSlotExist(String did, String date) {
        List<Slot> slotExist = new ArrayList<>();
        try {
            String sql = "SELECT slot_id\n" +
                    "FROM booking\n" +
                    "WHERE doctor_id = ? AND date = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(did));
            stm.setDate(2, Date.valueOf(date));
            rs = stm.executeQuery();
            while (rs.next()) {
                Slot slot = new Slot();
                slot.setId(rs.getInt("slot_id"));
                slotExist.add(slot);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return slotExist;
    }

    public void addMedical(MedicalRecord medicalRecord) {
        try {
            String sql = "INSERT INTO medical_record (booking_id, diagnosis, url, prescription) VALUES (?, ?, ?, ?);";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, medicalRecord.getBooking_id());
            stm.setString(2, medicalRecord.getDiagnosis());
            stm.setString(3, medicalRecord.getUrl());
            stm.setString(4, medicalRecord.getPrescription());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateMedical(MedicalRecord medicalRecord) {
        try {
            String sql = "UPDATE medical_record\n" +
                    "SET diagnosis = ?, url = ?, prescription = ?\n" +
                    "WHERE id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setString(1, medicalRecord.getDiagnosis());
            stm.setString(2, medicalRecord.getUrl());
            stm.setString(3, medicalRecord.getPrescription());
            stm.setInt(4, medicalRecord.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBill(Bill bill) {
        try {
            String sql = "INSERT INTO bill (medical_record_id, payment_status, pricePrescription, priceMedical, totalPrice) VALUES (?, ?, ?, ?, ?);";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, bill.getMedical_record_id());
            stm.setString(2, bill.getPayment_status());
            stm.setInt(3, (int) bill.getPricePrescription());
            stm.setInt(4, (int) bill.getPriceMedical());
            stm.setInt(5, (int) bill.getTotalPrice());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateBill(Bill bill) {
        try {
            String sql = "UPDATE bill\n" +
                    "SET payment_status = ?, pricePrescription = ?, priceMedical = ?, totalPrice = ?\n" +
                    "WHERE id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setString(1, bill.getPayment_status());
            stm.setInt(2, (int) bill.getPricePrescription());
            stm.setInt(3, (int) bill.getPriceMedical());
            stm.setInt(4, (int) bill.getTotalPrice());
            stm.setInt(5, bill.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MedicalRecord getTTByBillID(String bid) {
        try {
            String sql = "SELECT \n" +
                    "    d.id AS doctor_id,\n" +
                    "    d.name AS doctor_name,\n" +
                    "    d.url AS doctor_url, \n" +
                    "    d.specialty AS doctor_specialty,\n" +
                    "    rd.name AS rank_doctor,\n" +
                    "    p.id AS patient_id,\n" +
                    "    p.name AS patient_name, \n" +
                    "    p.url AS patient_url, \n" +
                    "    p.dob AS patient_date, \n" +
                    "    b.id AS booking_id, \n" +
                    "    b.date AS booking_date, \n" +
                    "    bill.id AS bill_id, \n" +
                    "    bill.priceMedical AS medical_price, \n" +
                    "    bill.pricePrescription AS prescription_price, \n" +
                    "    bill.totalPrice AS total_price, \n" +
                    "    bill.payment_status AS payment_status\n" +
                    "FROM \n" +
                    "    doctor AS d \n" +
                    "LEFT JOIN \n" +
                    "    rank_doctor AS rd ON rd.id = d.rank_id \n" +
                    "LEFT JOIN \n" +
                    "    booking AS b ON d.id = b.doctor_id \n" +
                    "LEFT JOIN \n" +
                    "    patient AS p ON b.patient_id = p.id \n" +
                    "LEFT JOIN \n" +
                    "    medical_record AS mr ON b.id = mr.booking_id \n" +
                    "LEFT JOIN \n" +
                    "    bill ON mr.id = bill.medical_record_id\n" +
                    "    Where bill.id = ? ;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(bid));
            rs = stm.executeQuery();
            if (rs.next()) {
                Rank rank = new Rank();
                rank.setName(rs.getString("rank_doctor"));
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("doctor_id"));
                doctor.setName(rs.getString("doctor_name"));
                doctor.setUrl(rs.getString("doctor_url"));
                doctor.setSpecialty(rs.getString("doctor_specialty"));
                doctor.setRanks(rank);
                Patient patient = new Patient();
                patient.setId(rs.getInt("patient_id"));
                patient.setName(rs.getString("patient_name"));
                patient.setUrl(rs.getString("patient_url"));
                patient.setDob(rs.getDate("patient_date"));
                Booking booking = new Booking();
                booking.setId(rs.getInt("booking_id"));
                booking.setDate(rs.getDate("booking_date"));
                booking.setDoctor(doctor);
                booking.setPatient(patient);
                Bill bill = new Bill();
                bill.setId(rs.getInt("bill_id"));
                bill.setPricePrescription(rs.getInt("prescription_price"));
                bill.setPriceMedical(rs.getInt("medical_price"));
                bill.setTotalPrice(rs.getInt("total_price"));
                bill.setPayment_status(rs.getString("payment_status"));
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setBill(bill);
                medicalRecord.setBooking(booking);
                return medicalRecord;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public MedicalRecord getTTByMedicalID(String bid) {
        try {
            String sql = "SELECT \n" +
                    "    d.id AS doctor_id,\n" +
                    "    d.name AS doctor_name,\n" +
                    "    d.url AS doctor_url,\n" +
                    "    d.specialty AS doctor_specialty,\n" +
                    "    rd.name AS rank_doctor,\n" +
                    "    p.id AS patient_id,\n" +
                    "    p.name AS patient_name, \n" +
                    "    p.url AS patient_url, \n" +
                    "    p.dob AS patient_date, \n" +
                    "    b.id AS booking_id, \n" +
                    "    b.date AS booking_date, \n" +
                    "    bill.id AS bill_id, \n" +
                    "    bill.priceMedical AS medical_price, \n" +
                    "    bill.pricePrescription AS prescription_price, \n" +
                    "    bill.totalPrice AS total_price, \n" +
                    "    bill.payment_status AS payment_status,\n" +
                    "    mr.id AS medical_record_id,\n" +
                    "    mr.diagnosis AS medical_record_diagnosis,\n" +
                    "    mr.url AS medical_record_url,\n" +
                    "    mr.prescription AS medical_record_prescription\n" +
                    "FROM \n" +
                    "    doctor AS d \n" +
                    "LEFT JOIN \n" +
                    "    rank_doctor AS rd ON rd.id = d.rank_id\n" +
                    "LEFT JOIN \n" +
                    "    booking AS b ON d.id = b.doctor_id\n" +
                    "LEFT JOIN \n" +
                    "    patient AS p ON b.patient_id = p.id\n" +
                    "LEFT JOIN \n" +
                    "    medical_record AS mr ON b.id = mr.booking_id\n" +
                    "LEFT JOIN \n" +
                    "    bill ON mr.id = bill.medical_record_id\n" +
                    "    Where mr.id = ? ;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(bid));
            rs = stm.executeQuery();
            if (rs.next()) {
                Rank rank = new Rank();
                rank.setName(rs.getString("rank_doctor"));
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("doctor_id"));
                doctor.setName(rs.getString("doctor_name"));
                doctor.setUrl(rs.getString("doctor_url"));
                doctor.setSpecialty(rs.getString("doctor_specialty"));
                doctor.setRanks(rank);
                Patient patient = new Patient();
                patient.setId(rs.getInt("patient_id"));
                patient.setName(rs.getString("patient_name"));
                patient.setUrl(rs.getString("patient_url"));
                patient.setDob(rs.getDate("patient_date"));
                Booking booking = new Booking();
                booking.setId(rs.getInt("booking_id"));
                booking.setDate(rs.getDate("booking_date"));
                booking.setDoctor(doctor);
                booking.setPatient(patient);
                Bill bill = new Bill();
                bill.setId(rs.getInt("bill_id"));
                bill.setPricePrescription(rs.getInt("prescription_price"));
                bill.setPriceMedical(rs.getInt("medical_price"));
                bill.setTotalPrice(rs.getInt("total_price"));
                bill.setPayment_status(rs.getString("payment_status"));
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setId(rs.getInt("medical_record_id"));
                medicalRecord.setUrl(rs.getString("medical_record_url"));
                medicalRecord.setPrescription(rs.getString("medical_record_prescription"));
                medicalRecord.setDiagnosis(rs.getString("medical_record_diagnosis"));
                medicalRecord.setBill(bill);
                medicalRecord.setBooking(booking);
                return medicalRecord;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public MedicalRecord getTTByBookingID(String bid) {
        try {
            String sql = "SELECT \n" +
                    "    d.id AS doctor_id,\n" +
                    "    d.name AS doctor_name,\n" +
                    "    d.url AS doctor_url, \n" +
                    "    d.specialty AS doctor_specialty,\n" +
                    "    rd.name AS rank_doctor,\n" +
                    "    p.id AS patient_id,\n" +
                    "    p.name AS patient_name, \n" +
                    "    p.url AS patient_url, \n" +
                    "    p.dob AS patient_date, \n" +
                    "    b.id AS booking_id, \n" +
                    "    b.date AS booking_date, \n" +
                    "    bill.id AS bill_id, \n" +
                    "    bill.priceMedical AS medical_price, \n" +
                    "    bill.pricePrescription AS prescription_price, \n" +
                    "    bill.totalPrice AS total_price, \n" +
                    "    bill.payment_status AS payment_status,\n" +
                    "    mr.id AS medical_record_id,\n" +
                    "    mr.diagnosis AS medical_record_diagnosis,\n" +
                    "\tmr.url AS medical_record_url,\n" +
                    "    mr.prescription AS medical_record_prescription\n" +
                    "FROM \n" +
                    "    doctor AS d \n" +
                    "LEFT JOIN \n" +
                    "    rank_doctor AS rd ON rd.id = d.rank_id\n" +
                    "LEFT JOIN \n" +
                    "    booking AS b ON d.id = b.doctor_id\n" +
                    "LEFT JOIN \n" +
                    "    patient AS p ON b.patient_id = p.id\n" +
                    "LEFT JOIN \n" +
                    "    medical_record AS mr ON b.id = mr.booking_id\n" +
                    "LEFT JOIN \n" +
                    "    bill ON mr.id = bill.medical_record_id\n" +
                    "WHERE \n" +
                    "    b.id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(bid));
            rs = stm.executeQuery();
            if (rs.next()) {
                Rank rank = new Rank();
                rank.setName(rs.getString("rank_doctor"));
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("doctor_id"));
                doctor.setUrl(rs.getString("doctor_url"));
                doctor.setName(rs.getString("doctor_name"));
                doctor.setSpecialty(rs.getString("doctor_specialty"));
                doctor.setRanks(rank);
                Patient patient = new Patient();
                patient.setId(rs.getInt("patient_id"));
                patient.setName(rs.getString("patient_name"));
                patient.setUrl(rs.getString("patient_url"));
                patient.setDob(rs.getDate("patient_date"));
                Booking booking = new Booking();
                booking.setId(rs.getInt("booking_id"));
                booking.setDate(rs.getDate("booking_date"));
                booking.setDoctor(doctor);
                booking.setPatient(patient);
                Bill bill = new Bill();
                bill.setId(rs.getInt("bill_id"));
                bill.setPricePrescription(rs.getInt("prescription_price"));
                bill.setPriceMedical(rs.getInt("medical_price"));
                bill.setTotalPrice(rs.getInt("total_price"));
                bill.setPayment_status(rs.getString("payment_status"));
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setBooking_id(rs.getInt("booking_id"));
                medicalRecord.setId(rs.getInt("medical_record_id"));
                medicalRecord.setUrl(rs.getString("medical_record_url"));
                medicalRecord.setPrescription(rs.getString("medical_record_prescription"));
                medicalRecord.setDiagnosis(rs.getString("medical_record_diagnosis"));
                medicalRecord.setBill(bill);
                medicalRecord.setBooking(booking);
                return medicalRecord;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<MedicalRecord> invoiceList(Doctor doctor) {
        List<MedicalRecord> getInvoiceList = new ArrayList<>();
        try {
            String sql = "SELECT b.id AS bill_id, b.totalPrice, b.payment_status, bd.id AS booking, bd.date, p.*\n" +
                    "FROM bill b\n" +
                    "JOIN medical_record mr ON b.medical_record_id = mr.id\n" +
                    "JOIN booking bd ON mr.booking_id = bd.id\n" +
                    "JOIN patient p ON bd.patient_id = p.id\n" +
                    "JOIN doctor d ON d.id = bd.doctor_id\n" +
                    "WHERE d.id = ? and b.payment_status = 'Paid';";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, doctor.getId());
            rs = stm.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setUrl(rs.getString("url"));
                patient.setName(rs.getString("name"));
                patient.setGender(rs.getString("gender"));
                patient.setDob(rs.getDate("dob"));
                patient.setRankId(rs.getInt("rank_id"));
                Bill bill = new Bill();
                bill.setId(rs.getInt("bill_id"));
                bill.setTotalPrice(rs.getFloat("totalPrice"));
                bill.setPayment_status(rs.getString("payment_status"));
                Booking booking = new Booking();
                booking.setId(rs.getInt("booking"));
                booking.setDate(rs.getDate("date"));
                booking.setPatient(patient);
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setBooking(booking);
                medicalRecord.setBill(bill);
                getInvoiceList.add(medicalRecord);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getInvoiceList;
    }

    public List<MedicalRecord> bookingList(Doctor doctor) {
        List<MedicalRecord> list = new ArrayList<>();
        try {
            String sql = "SELECT \n" +
                    "    rd.name AS rank_patient,\n" +
                    "    p.id AS patient_id,\n" +
                    "    p.name AS patient_name, \n" +
                    "    p.url AS patient_url, \n" +
                    "    p.dob AS patient_date, \n" +
                    "    b.id AS booking_id, \n" +
                    "    b.date AS booking_date, \n" +
                    "    b.status AS booking_status, \n" +
                    "    bill.id AS bill_id, \n" +
                    "    bill.priceMedical AS medical_price, \n" +
                    "    bill.pricePrescription AS prescription_price, \n" +
                    "    bill.totalPrice AS total_price, \n" +
                    "    bill.payment_status AS payment_status,\n" +
                    "    mr.id AS medical_record_id,\n" +
                    "    mr.diagnosis AS medical_record_diagnosis,\n" +
                    "\tmr.url AS medical_record_url,\n" +
                    "    mr.prescription AS medical_record_prescription\n" +
                    "FROM \n" +
                    "    doctor AS d \n" +
                    "LEFT JOIN \n" +
                    "    booking AS b ON d.id = b.doctor_id\n" +
                    "LEFT JOIN \n" +
                    "    patient AS p ON b.patient_id = p.id\n" +
                    "LEFT JOIN \n" +
                    "    rank_patient AS rd ON rd.id = p.rank_id\n" +
                    "LEFT JOIN \n" +
                    "    medical_record AS mr ON b.id = mr.booking_id\n" +
                    "LEFT JOIN \n" +
                    "    bill ON mr.id = bill.medical_record_id\n" +
                    "WHERE \n" +
                    "    d.id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, doctor.getId());
            rs = stm.executeQuery();
            while (rs.next()) {
                Rank rank = new Rank();
                rank.setName(rs.getString("rank_patient"));
                Patient patient = new Patient();
                patient.setId(rs.getInt("patient_id"));
                patient.setUrl(rs.getString("patient_url"));
                patient.setName(rs.getString("patient_name"));
                patient.setDob(rs.getDate("patient_date"));
                patient.setRanks(rank);
                Booking booking = new Booking();
                booking.setId(rs.getInt("booking_id"));
                booking.setDate(rs.getDate("booking_date"));
                booking.setStatus(rs.getString("booking_status"));
                booking.setDoctor(doctor);
                booking.setPatient(patient);
                Bill bill = new Bill();
                bill.setId(rs.getInt("bill_id"));
                bill.setPricePrescription(rs.getInt("prescription_price"));
                bill.setPriceMedical(rs.getInt("medical_price"));
                bill.setTotalPrice(rs.getInt("total_price"));
                bill.setPayment_status(rs.getString("payment_status"));
                MedicalRecord medicalRecord = new MedicalRecord();
                medicalRecord.setBooking_id(rs.getInt("booking_id"));
                medicalRecord.setId(rs.getInt("medical_record_id"));
                medicalRecord.setUrl(rs.getString("medical_record_url"));
                medicalRecord.setPrescription(rs.getString("medical_record_prescription"));
                medicalRecord.setDiagnosis(rs.getString("medical_record_diagnosis"));
                medicalRecord.setBill(bill);
                medicalRecord.setBooking(booking);
                list.add(medicalRecord);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
