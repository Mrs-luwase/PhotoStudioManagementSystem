package rw.ac.auca.photostudiomanagementsystem.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import rw.ac.auca.photostudiomanagementsystem.model.StaffMember;
import rw.ac.auca.photostudiomanagementsystem.util.HibernateUtil;

import java.util.List;

public class StaffMemberDao {

    HibernateUtil hibernateUtil = new HibernateUtil();

    public StaffMember saveStaffMember(StaffMember theStaff){
        Session ss = hibernateUtil.getSessionFactory().openSession();
        Transaction tr = ss.beginTransaction();
        ss.save(theStaff);
        tr.commit();
        ss.close();
        return theStaff;
    }

    public List<StaffMember> findAllStaffMembers(){
        Session ss = hibernateUtil.getSessionFactory().openSession();
        List<StaffMember> staff = ss.createQuery("SELECT s FROM StaffMember s").list();
        ss.close();
        return staff;
    }

    public StaffMember findStaffMemberById(String staffId){
        Session ss = hibernateUtil.getSessionFactory().openSession();
        StaffMember staff = ss.get(StaffMember.class, staffId);
        ss.close();
        return staff;
    }

    public StaffMember updateStaffMember(StaffMember theStaff){
        Session ss = hibernateUtil.getSessionFactory().openSession();
        Transaction tr = ss.beginTransaction();
        ss.update(theStaff);
        tr.commit();
        ss.close();
        return theStaff;
    }

    public void deleteStaffMember(String staffId){
        Session ss = hibernateUtil.getSessionFactory().openSession();
        Transaction tr = ss.beginTransaction();
        StaffMember staff = ss.get(StaffMember.class, staffId);
        if (staff != null) {
            ss.delete(staff);
        }
        tr.commit();
        ss.close();
    }
}