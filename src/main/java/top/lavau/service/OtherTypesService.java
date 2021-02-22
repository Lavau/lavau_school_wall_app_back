package top.lavau.service;

import top.lavau.controller.login.OtherTypesController;
import top.lavau.entity.MixedData;

public interface OtherTypesService {

    void insertTypeData(OtherTypesController.Form form);

    MixedData getMixedDataByIdAndTypeId(String id, Integer typeId);

    MixedData getMixedDataById(String id);

    void updateMixedData(MixedData mixedData);
}
