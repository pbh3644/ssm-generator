<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package_path}.mapper.${class_name}Mapper">
    <resultMap type="${entity_package}.${class_name}" id="${class_name?uncap_first}ResultMap">
		<#list table_column as c>
            <result property="${c.nameJ}" column="${c.name?upper_case}"/>
        </#list>
    </resultMap>

    <sql id="table_columns">
		<#list table_column as c>
            ${c.name?upper_case}<#if c_has_next>,</#if>
        </#list>
    </sql>
    <sql id="entity_properties">
		<#list table_column as c>
            ${r"#"}{${c.nameJ}}<#if c_has_next>,</#if>
        </#list>
    </sql>

    <!-- 使用like用法：columnName like concat('%',${r"#"}{columnName},'%') -->
    <sql id="page_where">
        <trim prefix="where DELETE_FLAG=0" prefixOverrides="and | or ">
		<#list table_column as c>
			<#if (c.type=="String")>
            <if test="${c.nameJ} != null and ${c.nameJ} != ''">and ${c.name?upper_case} = ${r"#"}{${c.nameJ}}</if>
            <#elseif (c.type=="Long")>
               <if test="${c.nameJ} != null and ${c.nameJ} != 0">and ${c.name?upper_case} = ${r"#"}{${c.nameJ}}</if>
            <#else>
                <if test="${c.nameJ} != null">and ${c.name?upper_case} = ${r"#"}{${c.nameJ}}</if>
            </#if>
        </#list>
        </trim>
    </sql>

    <insert id="insert" parameterType="${entity_package}.${class_name}">
        insert into ${table_name}(
        <include refid="table_columns"/>
        )
        values (
        <include refid="entity_properties"/>
        )
    </insert>

    <insert id="insertBatch" parameterType="java.util.List">
        insert into ${table_name}(
        <include refid="table_columns"/>
        )
        values
        <foreach collection="list" item="item" index="index" separator=",">
            (
		<#list table_column as c>
            ${r"#"}{item.${c.nameJ}}<#if c_has_next>,</#if>
        </#list>)
        </foreach>
    </insert>

    <delete id="delete" parameterType="java.lang.Long">
        delete from ${table_name}
        where ${table_column[0].name} = ${r"#"}{${table_column[0].nameJ}}
    </delete>

    <update id="deleteBatch">
        update ${table_name} set DELETE_FLAG = 1
        where ${table_column[0].name} in
        <foreach collection="array" item="id" open="(" separator="," close=")">
        ${r"#"}{id}
        </foreach>
    </update>

    <update id="deleteLogic" parameterType="java.lang.Long">
        update ${table_name} set DELETE_FLAG = 1
        where ${table_column[0].name} = ${r"#"}{${table_column[0].nameJ}}
    </update>

    <update id="update" parameterType="${entity_package}.${class_name}">
        update ${table_name}
        <trim prefix="set" suffixOverrides=",">
		<#list table_column as c><#if (c_index>=1)>
            <#if (c.type=="String")>
            <if test="${c.nameJ} != null and ${c.nameJ} != ''">${c.name?upper_case} = ${r"#"}{${c.nameJ}},</if>
            <#elseif (c.type=="Long")>
            <if test="${c.nameJ} != null and ${c.nameJ} != 0">${c.name?upper_case} = ${r"#"}{${c.nameJ}},</if>
            <#else>
            <if test="${c.nameJ} != null">and ${c.name?upper_case} = ${r"#"}{${c.nameJ}},</if>
            </#if>
        </#if></#list>
        </trim>
        <where>${table_column[0].name} = ${r"#"}{${table_column[0].nameJ}}</where>
    </update>

    <update id="updateBatch" parameterType="java.util.List">
        <foreach collection="list" item="item" index="index" separator=",">
            update ${table_name}
            <trim prefix="set" suffixOverrides=",">
			<#list table_column as c>
				<#if (c_index>=1)>
                    <#if (c.type=="String")>
						<if test="item.${c.nameJ} != null and item.${c.nameJ} != ''">${c.name?upper_case} = ${r"#"}
                            {item.${c.nameJ}},
                        </if>
                    <#elseif (c.type=="Long")>
                        <if test="item.${c.nameJ} != null and item.${c.nameJ} != 0">${c.name?upper_case} = ${r"#"}
                            {item.${c.nameJ}},
                        </if>
                    <#else >
                        <if test="item.${c.nameJ} != null">${c.name?upper_case} = ${r"#"}{item.${c.nameJ}},</if>
                    </#if>
                </#if>
            </#list>
            </trim>
            <where>${table_column[0].name} = ${r"#"}{item.${table_column[0].nameJ}}</where>
        </foreach>
    </update>

    <select id="findAll" resultMap="${class_name?uncap_first}ResultMap">
        select
        <include refid="table_columns"/>
        from ${table_name}
    </select>

    <select id="findList" resultMap="${class_name?uncap_first}ResultMap">
        select
        <include refid="table_columns"/>
        from ${table_name}
        <include refid="page_where"/>
    </select>

    <select id="getCount" resultType="int">
        select count(${table_column[0].name}) from ${table_name}
        <include refid="page_where"/>
    </select>


    <select id="get" resultMap="${class_name?uncap_first}ResultMap" parameterType="java.lang.Long">
        select
        <include refid="table_columns"/>
        from ${table_name}
        where ${table_column[0].name} = ${r"#"}{id} and DELETE_FLAG=0
    </select>

    <!-- 其他自定义SQL -->
</mapper>
