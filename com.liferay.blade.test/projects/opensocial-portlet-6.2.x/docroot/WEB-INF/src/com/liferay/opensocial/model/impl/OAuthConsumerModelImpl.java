/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.opensocial.model.impl;

import com.liferay.opensocial.model.OAuthConsumer;
import com.liferay.opensocial.model.OAuthConsumerModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the OAuthConsumer service. Represents a row in the &quot;OpenSocial_OAuthConsumer&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.opensocial.model.OAuthConsumerModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OAuthConsumerImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuthConsumerImpl
 * @see com.liferay.opensocial.model.OAuthConsumer
 * @see com.liferay.opensocial.model.OAuthConsumerModel
 * @generated
 */
public class OAuthConsumerModelImpl extends BaseModelImpl<OAuthConsumer>
	implements OAuthConsumerModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a o auth consumer model instance should use the {@link com.liferay.opensocial.model.OAuthConsumer} interface instead.
	 */
	public static final String TABLE_NAME = "OpenSocial_OAuthConsumer";
	public static final Object[][] TABLE_COLUMNS = {
			{ "oAuthConsumerId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "gadgetKey", Types.VARCHAR },
			{ "serviceName", Types.VARCHAR },
			{ "consumerKey", Types.VARCHAR },
			{ "consumerSecret", Types.CLOB },
			{ "keyType", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table OpenSocial_OAuthConsumer (oAuthConsumerId LONG not null primary key,companyId LONG,createDate DATE null,modifiedDate DATE null,gadgetKey VARCHAR(75) null,serviceName VARCHAR(75) null,consumerKey VARCHAR(75) null,consumerSecret TEXT null,keyType VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table OpenSocial_OAuthConsumer";
	public static final String ORDER_BY_JPQL = " ORDER BY oAuthConsumer.serviceName ASC";
	public static final String ORDER_BY_SQL = " ORDER BY OpenSocial_OAuthConsumer.serviceName ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.opensocial.model.OAuthConsumer"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.opensocial.model.OAuthConsumer"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.opensocial.model.OAuthConsumer"),
			true);
	public static long GADGETKEY_COLUMN_BITMASK = 1L;
	public static long SERVICENAME_COLUMN_BITMASK = 2L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.opensocial.model.OAuthConsumer"));

	public OAuthConsumerModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _oAuthConsumerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOAuthConsumerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _oAuthConsumerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return OAuthConsumer.class;
	}

	@Override
	public String getModelClassName() {
		return OAuthConsumer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("oAuthConsumerId", getOAuthConsumerId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("gadgetKey", getGadgetKey());
		attributes.put("serviceName", getServiceName());
		attributes.put("consumerKey", getConsumerKey());
		attributes.put("consumerSecret", getConsumerSecret());
		attributes.put("keyType", getKeyType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long oAuthConsumerId = (Long)attributes.get("oAuthConsumerId");

		if (oAuthConsumerId != null) {
			setOAuthConsumerId(oAuthConsumerId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String gadgetKey = (String)attributes.get("gadgetKey");

		if (gadgetKey != null) {
			setGadgetKey(gadgetKey);
		}

		String serviceName = (String)attributes.get("serviceName");

		if (serviceName != null) {
			setServiceName(serviceName);
		}

		String consumerKey = (String)attributes.get("consumerKey");

		if (consumerKey != null) {
			setConsumerKey(consumerKey);
		}

		String consumerSecret = (String)attributes.get("consumerSecret");

		if (consumerSecret != null) {
			setConsumerSecret(consumerSecret);
		}

		String keyType = (String)attributes.get("keyType");

		if (keyType != null) {
			setKeyType(keyType);
		}
	}

	@Override
	public long getOAuthConsumerId() {
		return _oAuthConsumerId;
	}

	@Override
	public void setOAuthConsumerId(long oAuthConsumerId) {
		_oAuthConsumerId = oAuthConsumerId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@Override
	public String getGadgetKey() {
		if (_gadgetKey == null) {
			return StringPool.BLANK;
		}
		else {
			return _gadgetKey;
		}
	}

	@Override
	public void setGadgetKey(String gadgetKey) {
		_columnBitmask |= GADGETKEY_COLUMN_BITMASK;

		if (_originalGadgetKey == null) {
			_originalGadgetKey = _gadgetKey;
		}

		_gadgetKey = gadgetKey;
	}

	public String getOriginalGadgetKey() {
		return GetterUtil.getString(_originalGadgetKey);
	}

	@Override
	public String getServiceName() {
		if (_serviceName == null) {
			return StringPool.BLANK;
		}
		else {
			return _serviceName;
		}
	}

	@Override
	public void setServiceName(String serviceName) {
		_columnBitmask = -1L;

		if (_originalServiceName == null) {
			_originalServiceName = _serviceName;
		}

		_serviceName = serviceName;
	}

	public String getOriginalServiceName() {
		return GetterUtil.getString(_originalServiceName);
	}

	@Override
	public String getConsumerKey() {
		if (_consumerKey == null) {
			return StringPool.BLANK;
		}
		else {
			return _consumerKey;
		}
	}

	@Override
	public void setConsumerKey(String consumerKey) {
		_consumerKey = consumerKey;
	}

	@Override
	public String getConsumerSecret() {
		if (_consumerSecret == null) {
			return StringPool.BLANK;
		}
		else {
			return _consumerSecret;
		}
	}

	@Override
	public void setConsumerSecret(String consumerSecret) {
		_consumerSecret = consumerSecret;
	}

	@Override
	public String getKeyType() {
		if (_keyType == null) {
			return StringPool.BLANK;
		}
		else {
			return _keyType;
		}
	}

	@Override
	public void setKeyType(String keyType) {
		_keyType = keyType;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			OAuthConsumer.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public OAuthConsumer toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (OAuthConsumer)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		OAuthConsumerImpl oAuthConsumerImpl = new OAuthConsumerImpl();

		oAuthConsumerImpl.setOAuthConsumerId(getOAuthConsumerId());
		oAuthConsumerImpl.setCompanyId(getCompanyId());
		oAuthConsumerImpl.setCreateDate(getCreateDate());
		oAuthConsumerImpl.setModifiedDate(getModifiedDate());
		oAuthConsumerImpl.setGadgetKey(getGadgetKey());
		oAuthConsumerImpl.setServiceName(getServiceName());
		oAuthConsumerImpl.setConsumerKey(getConsumerKey());
		oAuthConsumerImpl.setConsumerSecret(getConsumerSecret());
		oAuthConsumerImpl.setKeyType(getKeyType());

		oAuthConsumerImpl.resetOriginalValues();

		return oAuthConsumerImpl;
	}

	@Override
	public int compareTo(OAuthConsumer oAuthConsumer) {
		int value = 0;

		value = getServiceName().compareTo(oAuthConsumer.getServiceName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OAuthConsumer)) {
			return false;
		}

		OAuthConsumer oAuthConsumer = (OAuthConsumer)obj;

		long primaryKey = oAuthConsumer.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		OAuthConsumerModelImpl oAuthConsumerModelImpl = this;

		oAuthConsumerModelImpl._originalGadgetKey = oAuthConsumerModelImpl._gadgetKey;

		oAuthConsumerModelImpl._originalServiceName = oAuthConsumerModelImpl._serviceName;

		oAuthConsumerModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<OAuthConsumer> toCacheModel() {
		OAuthConsumerCacheModel oAuthConsumerCacheModel = new OAuthConsumerCacheModel();

		oAuthConsumerCacheModel.oAuthConsumerId = getOAuthConsumerId();

		oAuthConsumerCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			oAuthConsumerCacheModel.createDate = createDate.getTime();
		}
		else {
			oAuthConsumerCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			oAuthConsumerCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			oAuthConsumerCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		oAuthConsumerCacheModel.gadgetKey = getGadgetKey();

		String gadgetKey = oAuthConsumerCacheModel.gadgetKey;

		if ((gadgetKey != null) && (gadgetKey.length() == 0)) {
			oAuthConsumerCacheModel.gadgetKey = null;
		}

		oAuthConsumerCacheModel.serviceName = getServiceName();

		String serviceName = oAuthConsumerCacheModel.serviceName;

		if ((serviceName != null) && (serviceName.length() == 0)) {
			oAuthConsumerCacheModel.serviceName = null;
		}

		oAuthConsumerCacheModel.consumerKey = getConsumerKey();

		String consumerKey = oAuthConsumerCacheModel.consumerKey;

		if ((consumerKey != null) && (consumerKey.length() == 0)) {
			oAuthConsumerCacheModel.consumerKey = null;
		}

		oAuthConsumerCacheModel.consumerSecret = getConsumerSecret();

		String consumerSecret = oAuthConsumerCacheModel.consumerSecret;

		if ((consumerSecret != null) && (consumerSecret.length() == 0)) {
			oAuthConsumerCacheModel.consumerSecret = null;
		}

		oAuthConsumerCacheModel.keyType = getKeyType();

		String keyType = oAuthConsumerCacheModel.keyType;

		if ((keyType != null) && (keyType.length() == 0)) {
			oAuthConsumerCacheModel.keyType = null;
		}

		return oAuthConsumerCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{oAuthConsumerId=");
		sb.append(getOAuthConsumerId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", gadgetKey=");
		sb.append(getGadgetKey());
		sb.append(", serviceName=");
		sb.append(getServiceName());
		sb.append(", consumerKey=");
		sb.append(getConsumerKey());
		sb.append(", consumerSecret=");
		sb.append(getConsumerSecret());
		sb.append(", keyType=");
		sb.append(getKeyType());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.opensocial.model.OAuthConsumer");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>oAuthConsumerId</column-name><column-value><![CDATA[");
		sb.append(getOAuthConsumerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>gadgetKey</column-name><column-value><![CDATA[");
		sb.append(getGadgetKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serviceName</column-name><column-value><![CDATA[");
		sb.append(getServiceName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>consumerKey</column-name><column-value><![CDATA[");
		sb.append(getConsumerKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>consumerSecret</column-name><column-value><![CDATA[");
		sb.append(getConsumerSecret());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>keyType</column-name><column-value><![CDATA[");
		sb.append(getKeyType());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = OAuthConsumer.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			OAuthConsumer.class
		};
	private long _oAuthConsumerId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _gadgetKey;
	private String _originalGadgetKey;
	private String _serviceName;
	private String _originalServiceName;
	private String _consumerKey;
	private String _consumerSecret;
	private String _keyType;
	private long _columnBitmask;
	private OAuthConsumer _escapedModel;
}