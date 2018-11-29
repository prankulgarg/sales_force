/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.model;

/**
 *
 * @author Admin
 */
public class Member extends GenericResponse{
    
    private int employeeId;
    private String employeeName;
    private String emailId;
    private String mobile;
    private String  alternatemobileNumber;
    private String adddress;
    private String joiningDate;
    private int roleId;
    private int designationId;
    private int distributerId;
    private int tsmId;
    private int asmId;
    private int managerId;
    private int cretedById;
    private String password;
    private String roleName;
    private String DistributerName;
    private String empId;
    private String state;
    private int reportTo;
    private String managerName;
	private String TsmName;
	private String asmName;
	private String reportToName;
	private String designationname;
	private String profilePic;
	private int updateById;
	private int stateId;
	private String visitCount;
   
    
    
    
    

	public String getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(String visitCount) {
		this.visitCount = visitCount;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getUpdateById() {
		return updateById;
	}

	public void setUpdateById(int updateById) {
		this.updateById = updateById;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getReportToName() {
		return reportToName;
	}

	public void setReportToName(String reportToName) {
		this.reportToName = reportToName;
	}

	public String getDesignationname() {
		return designationname;
	}

	public void setDesignationname(String designationname) {
		this.designationname = designationname;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getAsmName() {
		return asmName;
	}

	public void setAsmName(String asmName) {
		this.asmName = asmName;
	}

	public String getTsmName() {
		return TsmName;
	}

	public void setTsmName(String tsmName) {
		TsmName = tsmName;
	}

   
    
    
    public int getReportTo() {
		return reportTo;
	}

	public void setReportTo(int reportTo) {
		this.reportTo = reportTo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDistributerName() {
		return DistributerName;
	}

	public void setDistributerName(String distributerName) {
		DistributerName = distributerName;
	}

	public int getRetailerCount() {
		return retailerCount;
	}

	public void setRetailerCount(int retailerCount) {
		this.retailerCount = retailerCount;
	}

	private int retailerCount;
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
	public String toString() {
		return "Member [employeeId=" + employeeId + ", employeeName=" + employeeName + ", emailId=" + emailId
				+ ", mobile=" + mobile + ", alternatemobileNumber=" + alternatemobileNumber + ", adddress=" + adddress
				+ ", joiningDate=" + joiningDate + ", roleId=" + roleId + ", designationId=" + designationId
				+ ", distributerId=" + distributerId + ", tsmId=" + tsmId + ", asmId=" + asmId + ", managerId="
				+ managerId + ", cretedById=" + cretedById + ", password=" + password + ", roleName=" + roleName
				+ ", DistributerName=" + DistributerName + ", empId=" + empId + ", state=" + state + ", reportTo="
				+ reportTo + ", managerName=" + managerName + ", TsmName=" + TsmName + ", asmName=" + asmName
				+ ", retailerCount=" + retailerCount + "]";
	}

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAlternatemobileNumber() {
        return alternatemobileNumber;
    }

    public void setAlternatemobileNumber(String alternatemobileNumber) {
        this.alternatemobileNumber = alternatemobileNumber;
    }

    public String getAdddress() {
        return adddress;
    }

    public void setAdddress(String adddress) {
        this.adddress = adddress;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getDesignationId() {
        return designationId;
    }

    public void setDesignationId(int designationId) {
        this.designationId = designationId;
    }

    public int getDistributerId() {
        return distributerId;
    }

    public void setDistributerId(int distributerId) {
        this.distributerId = distributerId;
    }

    public int getTsmId() {
        return tsmId;
    }

    public void setTsmId(int tsmId) {
        this.tsmId = tsmId;
    }

    public int getAsmId() {
        return asmId;
    }

    public void setAsmId(int asmId) {
        this.asmId = asmId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getCretedById() {
        return cretedById;
    }

    public void setCretedById(int cretedById) {
        this.cretedById = cretedById;
    }
    
    
    
    
    
}
