package com.github.vendigo.charon.domain;

import org.apache.camel.component.jpa.Consumed;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Sample implements Serializable {
    @Id
    private long fileId;
    @Id
    private long lineNumber;
    @Column
    private boolean loaded = false;
    @Column
    private boolean valid = false;
    @Column
    private Date businessDate;
    @Column
    private String account;
    @Column
    private String accountName;
    @Column
    private String someSecurity;
    @Column
    private Double quantity;
    @Column
    private Boolean shouldReportToAldebaranHeadQuarters;

    public long getFileId() {
        return fileId;
    }

    public long getLineNumber() {
        return lineNumber;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public boolean isValid() {
        return valid;
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public String getAccount() {
        return account;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getSomeSecurity() {
        return someSecurity;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Boolean getShouldReportToAldebaranHeadQuarters() {
        return shouldReportToAldebaranHeadQuarters;
    }

    @Consumed
    public void markLoaded() {
        this.loaded = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sample sample = (Sample) o;
        return fileId == sample.fileId &&
                lineNumber == sample.lineNumber &&
                loaded == sample.loaded &&
                valid == sample.valid &&
                Objects.equals(businessDate, sample.businessDate) &&
                Objects.equals(account, sample.account) &&
                Objects.equals(accountName, sample.accountName) &&
                Objects.equals(someSecurity, sample.someSecurity) &&
                Objects.equals(quantity, sample.quantity) &&
                Objects.equals(shouldReportToAldebaranHeadQuarters, sample.shouldReportToAldebaranHeadQuarters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileId, lineNumber, loaded, valid, businessDate, account, accountName, someSecurity,
                quantity, shouldReportToAldebaranHeadQuarters);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fileId", fileId)
                .append("lineNumber", lineNumber)
                .append("loaded", loaded)
                .append("valid", valid)
                .append("businessDate", businessDate)
                .append("account", account)
                .append("accountName", accountName)
                .append("someSecurity", someSecurity)
                .append("quantity", quantity)
                .append("shouldReportToAldebaranHeadQuarters", shouldReportToAldebaranHeadQuarters)
                .toString();
    }
}
