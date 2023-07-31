package com.laptrinhjavaweb.dto.respone;

public class StaffResponseDTO {
    private String fullName;
    private Long staffId;
/*    nếu staff quản lý tòa nhà id
    thì checked sẽ chứa "checked" , còn không thì ""*/
    private String checked;

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}
