package rw.ac.auca.photostudiomanagementsystem.bean;

import rw.ac.auca.photostudiomanagementsystem.dao.StaffMemberDao;
import rw.ac.auca.photostudiomanagementsystem.model.StaffMember;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@ManagedBean
@SessionScoped
public class StaffMemberBean {

    private StaffMemberDao staffDao = new StaffMemberDao();
    private List<StaffMember> staffList;
    private StaffMember staff = new StaffMember();

    @PostConstruct
    public void init() {
        staffList = staffDao.findAllStaffMembers();
    }

    public String prepareNew() {
        staff = new StaffMember();
        staff.setStaffId(generateStaffId());
        return "staffForm?faces-redirect=true";
    }

    private String generateStaffId() {
        int nextNumber = staffDao.findAllStaffMembers().size() + 1;
        return String.format("ST%03d", nextNumber);
    }

    public String prepareEdit(String staffId) {
        staff = staffDao.findStaffMemberById(staffId);
        return "staffForm?faces-redirect=true";
    }

    public String saveStaff() {
        if (staff.getStaffId() == null || staff.getStaffId().trim().isEmpty()) {
            staff.setStaffId(generateStaffId());
        }
        staff.setCreatedAt(LocalDateTime.now());
        staff.setUpdatedAt(LocalDateTime.now());
        staffDao.saveStaffMember(staff);
        staffList = staffDao.findAllStaffMembers();
        return "staffConfirmation?faces-redirect=true";
    }

    public String updateStaff() {
        staff.setUpdatedAt(LocalDateTime.now());
        staffDao.updateStaffMember(staff);
        staffList = staffDao.findAllStaffMembers();
        return "staffConfirmation?faces-redirect=true";
    }

    public String deleteStaff(String staffId) {
        staffDao.deleteStaffMember(staffId);
        staffList = staffDao.findAllStaffMembers();
        return "staffList?faces-redirect=true";
    }

    // custom validator: Rwandan mobile format, 07 followed by 8 digits
    public void validatePhoneNumber(FacesContext context, UIComponent component, Object value) {
        String phone = (String) value;
        if (!phone.matches("^07[0-9]{8}$")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Phone number must start with 07 and have 10 digits total (e.g. 0788123456).", null));
        }
    }

    public List<StaffMember> getStaffList() { return staffList; }
    public StaffMember getStaff() { return staff; }
    public void setStaff(StaffMember staff) { this.staff = staff; }
}