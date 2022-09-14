package com.kai.smart.platform.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kai.smart.platform.util.AtlasConnectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.atlas.AtlasClientV2;
import org.apache.atlas.AtlasServiceException;
import org.apache.atlas.model.discovery.AtlasSearchResult;
import org.apache.atlas.model.instance.AtlasEntityHeader;
import org.apache.atlas.model.lineage.AtlasLineageInfo;
import org.apache.atlas.model.typedef.AtlasClassificationDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class AtlasService {
    @Autowired
    @Qualifier("mapper")
    ObjectMapper mapper;

    @Value("${atlas.rest.address}")
    String address;

    @Value("${atlas.rest.username}")
    String username;

    @Value("${atlas.rest.password}")
    String password;

    /**
     *
     * @param name
     * @return classification 해당하는 guid 반환
     * @throws AtlasServiceException
     */
    public String  getClassificationGuid(String name) throws AtlasServiceException {
        AtlasClientV2 atlasClientV2=null;
        AtlasClassificationDef classificationDef=null;
        try {
            atlasClientV2 = AtlasConnectionUtils.create(address, username, password);
            classificationDef
                    = atlasClientV2.getClassificationDefByName(name);

        }catch (Exception e){
            log.error("AtlasService >> getClassificationGuid >> ERROR : ",e);
        }finally {
            atlasClientV2.close();
        }
        return classificationDef.getGuid();
    }


    /**
     * 테이블 명에 해당하는 guid 반환
     * @param name
     * @return
     * @throws AtlasServiceException
     */
    public String  getTableGuid(String name) throws AtlasServiceException {
        AtlasClientV2 atlasClientV2=null;
        AtlasSearchResult result=null;
        try {
            atlasClientV2 = AtlasConnectionUtils.create(address, username, password);
            result = atlasClientV2.basicSearch("hive_table",
                    "", name, false, 2, 0);
        } catch (Exception e){
            log.error("AtlasService >> getTableGuid >> ERROR : ",e);
        }
        return result.getEntities().get(0).getGuid();
    }

    /**
     * guid에 해당하는 lineage 정보 반환
     * @param guid
     * @return
     * @throws AtlasServiceException
     */
    public String  getLineage(String guid) throws AtlasServiceException {
        AtlasClientV2 atlasClientV2= AtlasConnectionUtils.create(address,username,password);
        StringBuilder lineageBuilder = new StringBuilder();
        AtlasLineageInfo lineageInfo   = atlasClientV2.getLineageInfo(guid, AtlasLineageInfo.LineageDirection.BOTH, 0);
        Set<AtlasLineageInfo.LineageRelation> relations     = lineageInfo.getRelations();
        Map<String, AtlasEntityHeader>        guidEntityMap = lineageInfo.getGuidEntityMap();
        int i=0;
        for (AtlasLineageInfo.LineageRelation relation : relations) {

            AtlasEntityHeader fromEntity = guidEntityMap.get(relation.getFromEntityId());
            AtlasEntityHeader toEntity   = guidEntityMap.get(relation.getToEntityId());
            i++;
            lineageBuilder.append(String.valueOf(i)+": "+ fromEntity.getDisplayText() + "(" + fromEntity.getTypeName() + ") -> " +
                    toEntity.getDisplayText() + "(" + toEntity.getTypeName() + ")   ");

        }
        return lineageBuilder.toString();
    }

}
