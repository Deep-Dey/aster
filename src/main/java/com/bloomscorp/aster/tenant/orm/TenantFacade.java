package com.bloomscorp.aster.tenant.orm;

import com.bloomscorp.aster.tenant.contract.TenantContract;
import com.bloomscorp.behemoth.orm.BehemothORM;
import com.bloomscorp.nverse.pojo.NVERSE_AUTH_PROVIDER;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = TenantContract.TABLE)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(
	name = TenantContract.TABLE,
	uniqueConstraints = {
		@UniqueConstraint(
			name = "unique_uid",
			columnNames = TenantContract.UID
		),
		@UniqueConstraint(
			name = "unique_email",
			columnNames = TenantContract.EMAIL
		),
		@UniqueConstraint(
			name = "unique_contact_number",
			columnNames = TenantContract.CONTACT
		)
	}
)
public class TenantFacade extends BehemothORM implements NVerseTenant<USER_ROLE, AsterUserRole<USER_ROLE>> {

	@Column(
		name = TenantContract.UID,
		columnDefinition = "VARCHAR",
		length = 22,
		nullable = false
	)
	private String uid;

	@Column(
		name = TenantContract.EMAIL,
		columnDefinition = "VARCHAR",
		length = 150,
		nullable = false
	)
	private String email;

	@Column(
		name = TenantContract.CONTACT,
		columnDefinition = "VARCHAR",
		length = 20,
		nullable = false
	)
	@ColumnDefault("''")
	private String contactNumber;

	@Column(
		name = TenantContract.PASSWORD,
		columnDefinition = "VARCHAR",
		length = 120,
		nullable = false
	)
	private String password;

	@Column(
		name = TenantContract.NAME,
		columnDefinition = "VARCHAR",
		length = 150,
		nullable = false
	)
	private String name;

	@Column(
		name = TenantContract.LAST_ACCESS_TIME,
		columnDefinition = "BIGINT"
	)
	@ColumnDefault("0")
	@Min(
		value = 0,
		message = "Last access time cannot be less than 0"
	)
	private Long lastAccessTime;

	@Column(
		name = TenantContract.PROFILE_IMAGE_URL,
		columnDefinition = "VARCHAR",
		nullable = false
	)
	@ColumnDefault("default-profile-picture.svg")
	private String profileImageUrl = "default-profile-picture.svg";

	@Column(
		name = TenantContract.ACTIVE,
		columnDefinition = "BOOLEAN",
		nullable = false
	)
	@ColumnDefault("true")
	private boolean active = true;

	@Column(
		name = TenantContract.SUSPENDED,
		columnDefinition = "BOOLEAN",
		nullable = false
	)
	@ColumnDefault("false")
	private boolean suspended = false;

	@Column(
		name = TenantContract.DELETED,
		columnDefinition = "BOOLEAN",
		nullable = false
	)
	@ColumnDefault("false")
	private boolean deleted = false;

	@Enumerated(EnumType.STRING)
	@Column(
		name = TenantContract.PROVIDER,
		columnDefinition = "auth_provider_enum",
		nullable = false
	)
	@Type(PostgreSQLEnumType.class)
	@ColumnDefault("UNKNOWN")
	private NVERSE_AUTH_PROVIDER provider = NVERSE_AUTH_PROVIDER.UNKNOWN;

	@Transient
	private String decryptedEmail;

	@Override
	public List<AsterUserRole<USER_ROLE>> getRoles() {
		throw new UnsupportedOperationException("TenantFacade does not have access to user roles!");
	}
}
