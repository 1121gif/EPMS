package com.hong.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 考勤记录表
 * @TableName t_attendance
 */
@TableName(value ="t_attendance")
@Data
public class TAttendance implements Serializable {
    /**
     * 考勤记录的唯一标识符
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 考勤记录对应的员工ID，关联到t_employee表中的id字段
     */
    private Integer eid;

    /**
     * 员工姓名，从t_employee表中查询得到
     */
    private String employeename;

    /**
     * 员工所属部门，从t_employee表中查询得到
     */
    private Integer department;

    /**
     * 考勤日期
     */
    private Date attendancedate;

    /**
     * 上班时间
     */
    private Date ondutytime;

    /**
     * 下班时间
     */
    private Date offdutytime;

    /**
     * 工作时长（小时），根据上下班时间自动计算得到
     */
    private Double workinghours;

    /**
     * 备注信息
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TAttendance other = (TAttendance) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEid() == null ? other.getEid() == null : this.getEid().equals(other.getEid()))
            && (this.getEmployeename() == null ? other.getEmployeename() == null : this.getEmployeename().equals(other.getEmployeename()))
            && (this.getDepartment() == null ? other.getDepartment() == null : this.getDepartment().equals(other.getDepartment()))
            && (this.getAttendancedate() == null ? other.getAttendancedate() == null : this.getAttendancedate().equals(other.getAttendancedate()))
            && (this.getOndutytime() == null ? other.getOndutytime() == null : this.getOndutytime().equals(other.getOndutytime()))
            && (this.getOffdutytime() == null ? other.getOffdutytime() == null : this.getOffdutytime().equals(other.getOffdutytime()))
            && (this.getWorkinghours() == null ? other.getWorkinghours() == null : this.getWorkinghours().equals(other.getWorkinghours()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEid() == null) ? 0 : getEid().hashCode());
        result = prime * result + ((getEmployeename() == null) ? 0 : getEmployeename().hashCode());
        result = prime * result + ((getDepartment() == null) ? 0 : getDepartment().hashCode());
        result = prime * result + ((getAttendancedate() == null) ? 0 : getAttendancedate().hashCode());
        result = prime * result + ((getOndutytime() == null) ? 0 : getOndutytime().hashCode());
        result = prime * result + ((getOffdutytime() == null) ? 0 : getOffdutytime().hashCode());
        result = prime * result + ((getWorkinghours() == null) ? 0 : getWorkinghours().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", eid=").append(eid);
        sb.append(", employeename=").append(employeename);
        sb.append(", department=").append(department);
        sb.append(", attendancedate=").append(attendancedate);
        sb.append(", ondutytime=").append(ondutytime);
        sb.append(", offdutytime=").append(offdutytime);
        sb.append(", workinghours=").append(workinghours);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}