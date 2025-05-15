# Database Migration - Technical Notes & Interview Scenarios

---

## 1. What is Database Migration?
Database migration is the process of evolving the database schema (tables, columns, constraints, data) in a controlled, versioned, and repeatable way as your application evolves.

---

## 2. Tools Used
- **Flyway** (used in this project): A popular tool for managing schema migrations using versioned SQL scripts.
- **Liquibase**: Another common migration tool.

---

## 3. Best Practices
- **Version Control:** All migration scripts should be versioned and stored in source control.
- **Idempotency:** Migrations should be repeatable and not cause errors if run multiple times.
- **Backward Compatibility:** Design migrations so that old and new application versions can run during deployment (zero-downtime).
- **Testing:** Always test migrations in a staging environment before production.
- **Rollback:** Have a rollback plan or down scripts for destructive changes.
- **Backups:** Take database backups before running migrations in production.
- **Atomicity:** Each migration should be atomic—either fully applied or not at all.

---

## 4. Scenario-Based Interview Questions & Answers

### Scenario 1: Migration Failure During Deployment
**Q:** What would you do if a Flyway migration fails during a production deployment?

**A:**  
- Check Flyway and application logs for the error.
- If the migration is non-destructive, fix the script and re-run.
- If the migration is destructive (e.g., drops columns), restore the database from backup.
- Communicate with stakeholders about downtime or rollback.
- Add tests to prevent similar issues in the future.

---

### Scenario 2: Rolling Back a Destructive Migration
**Q:** How do you handle a situation where a migration script accidentally drops a critical table?

**A:**  
- Immediately stop further migrations and application writes.
- Restore the affected table/data from the latest backup.
- Review and fix the migration script.
- Implement a review process for migration scripts.
- Add a dry-run or validation step in CI/CD.

---

### Scenario 3: Zero-Downtime Migration
**Q:** How would you perform a schema migration that requires adding a non-nullable column to a large table, without downtime?

**A:**  
- Add the new column as nullable.
- Backfill data for existing rows in batches.
- Alter the column to be non-nullable once all rows are populated.
- Deploy application changes to use the new column.

---

### Scenario 4: Data Migration with Application Changes
**Q:** How do you coordinate application and schema changes that must go live together?

**A:**  
- Use feature flags to hide new features until both code and schema are ready.
- Deploy schema changes first (in a backward-compatible way).
- Deploy application changes.
- Remove feature flags and old code after verifying stability.

---

### Scenario 5: Handling Long-Running Migrations
**Q:** What if a migration script takes too long and causes downtime?

**A:**  
- Break the migration into smaller, incremental steps.
- Run heavy data migrations in the background, outside of the main migration process.
- Use online schema change tools (e.g., pt-online-schema-change for MySQL).
- Monitor migration progress and system health.

---

### Scenario 6: Migration Version Conflicts in a Team
**Q:** Two developers add migrations with the same version number. What happens and how do you prevent it?

**A:**  
- Flyway will fail with a version conflict.
- Use a naming convention and communicate within the team to avoid conflicts.
- Use a migration script generator or assign migration numbers via a CI tool.

---

### Scenario 7: Data Loss After Migration
**Q:** After a migration, some data is missing. How do you investigate and prevent this?

**A:**  
- Review the migration script for destructive operations.
- Check logs for errors or warnings during migration.
- Restore data from backup if possible.
- Add tests and dry-run migrations in staging to catch such issues early.

---

## 5. Troubleshooting Checklist
- **Migration fails:** Check logs, fix script, re-run, or restore from backup.
- **Data loss:** Restore from backup, review scripts, add tests.
- **Performance issues:** Break up large migrations, use online schema change tools.
- **Version conflicts:** Enforce unique versioning, use CI checks.
- **Rollback needed:** Use down scripts or restore from backup.
- **Production safety:** Always backup before migration, test in staging, monitor after deployment.

---

## 6. Key Takeaways for Interviews
- Always have a rollback and backup plan.
- Test migrations in a staging environment.
- Communicate migration plans and risks with your team.
- Automate and monitor migrations as part of CI/CD.
- Prefer additive, backward-compatible changes for zero-downtime deployments. 