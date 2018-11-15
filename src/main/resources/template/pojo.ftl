package ${package_path};

<#if (hasDateColumn)>
import java.util.Date;
</#if>
<#if (hasBigDecimalColumn)>
import java.math.BigDecimal;
</#if>

import com.pbh.journey.system.common.base.pojo.BaseEntity;
import lombok.Data;

/**
 * entity:${class_name}
 *
 * @author ${author}
 * @version 1.0
 * @since ${sysDate?date}
 */
@Data
public class ${class_name} extends BaseEntity<${class_name}> {
    private static final long serialVersionUID = 1L;

<#list table_column as c>
    /**
     * ${c.remark}
     */
    <#if (c.name!="id")>
        private ${c.type} ${c.nameJ};
        <#if (c.remark?exists && c.remark!="")></#if>
    </#if>
</#list>

    public ${class_name}() {
    }


}
