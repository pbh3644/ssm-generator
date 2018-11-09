package ${package_path}.service.impl;

import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import ${package_path}.service.${class_name}Service;
import ${package_path}.mapper.${class_name}Mapper;
import ${entity_package}.${class_name};
import org.springframework.stereotype.Service;

/**
 * ServiceImpl:${class_name}ServiceImpl
 *
 * @author ${author}
 * @version 1.0
 * @since ${sysDate?date}
 */
@Service("${class_name}Service")
public class ${class_name}ServiceImpl extends BaseServiceImpl<${class_name}Mapper, ${class_name}> implements ${class_name}Service {
    //@Resource
    //private ${class_name}Mapper ${class_name?uncap_first}Mapper;

}
