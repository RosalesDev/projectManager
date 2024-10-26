package com.st.project_manager.audit;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class AuditListener {

    @PrePersist
    public void setCreatedBy(Audit auditEntity) {
    	auditEntity.setCreatedBy(getAuthenticatedUser());
    }

    @PreUpdate
    public void setUpdatedBy(Audit auditEntity) {
        auditEntity.setModifiedBy(getAuthenticatedUser());
    }

    private Long getAuthenticatedUser() {
        return (long) 123;  // Valor de ejemplo
    }
}

