package homework_13_3012.storage.initor.fileinitor;

import homework_13_3012.cargo.domain.BasicCargo;
import homework_13_3012.carrier.domain.Carrier;
import homework_13_3012.common.company_exceptions.checked.InitStorageException;

import homework_13_3012.common.util.FileUtils;
import homework_13_3012.transportation.domain.Transportation;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StorageInitorInXMLWithSAX extends BaseStorageInitorInFile
{
    private static final String FILE = "/xmldata.xml";

    @Override
    public void storageInitor() throws InitStorageException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            EntitySAXHandler personHandler = new EntitySAXHandler();

            File file = FileUtils.createFileFromResource("xmldata", "xmldata-file", FILE);
            saxParser.parse(file, personHandler);


            Map<String, BasicCargo> cargoMap = personHandler.getParsedCargos();
            Map<String, Carrier> carrierMap = personHandler.getParsedCarriers();
            List<ParsedTransportation> transportations = personHandler.getParsedTransportations();
            setReferencesBetweenEntities(cargoMap, carrierMap, transportations);

            persistCargos(cargoMap.values());
            persistCarriers(carrierMap.values());
            List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
            persistTransportations(transportationList);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
