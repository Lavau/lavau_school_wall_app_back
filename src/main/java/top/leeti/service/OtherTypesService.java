package top.leeti.service;

import top.leeti.controller.login.OtherTypesController;
import top.leeti.entity.MixedData;

public interface OtherTypesService {

    void insertTypeData(OtherTypesController.Form form);

    MixedData getMixedDataByIdAndTypeId(String id, Integer typeId);

    MixedData getMixedDataById(String id);

    void updateMixedData(MixedData mixedData);
}
