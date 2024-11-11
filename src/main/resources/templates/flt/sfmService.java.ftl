package ${package.Service};

import ${package.Entity}.SFM${entity};
import ${package.Mapper}.SFM${table.mapperName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
*
* ${table.comment!} 服务实现类
*
*
* @author ${author}
* @since ${date}
*/
@Service
<#if kotlin>
open class ${table.serviceName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>() {

}
<#else>
public class SFM${table.serviceName} extends ${superServiceImplClass}<SFM${table.mapperName}, SFM${entity}>  {

}
</#if>
