package ${package_path};

<#if (hasDateColumn)>
import java.util.Date;
</#if>
<#if (hasBigDecimalColumn)>
import java.math.BigDecimal;
</#if>

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * entity:${class_name}
 *
 * @author ${author}
 * @version 1.0
 * @since ${sysDate?date}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ${class_name} implements Serializable {

<#list table_column as c>
    /**
     * ${c.remark}
     */
    private ${c.type} ${c.nameJ};
</#list>
}
