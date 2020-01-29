package homework_17_2701_optional.storage.initor.fileinitor;

import homework_17_2701_optional.cargo.domain.BasicCargo;
import homework_17_2701_optional.common.util.FileUtils;
import homework_17_2701_optional.carrier.domain.Carrier;
import homework_17_2701_optional.common.company_exceptions.checked.InitStorageException;
import homework_17_2701_optional.storage.initor.fileinitor.saxhandlers.saxhandlersstack.EntitySAXHandlerWithStack;
import homework_17_2701_optional.transportation.domain.Transportation;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
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

            //EntitySAXHandler saxHandler = new EntitySAXHandler();
            EntitySAXHandlerWithStack saxHandler= new EntitySAXHandlerWithStack();

            File file = FileUtils.createFileFromResource("xmldata", "xmldata-file", FILE);
            saxParser.parse(file, saxHandler);

            Map<String, BasicCargo> cargoMap = saxHandler.getParsedCargos();
            Map<String, Carrier> carrierMap = saxHandler.getParsedCarriers();
            List<ParsedTransportation> transportations = saxHandler.getParsedTransportations();
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
