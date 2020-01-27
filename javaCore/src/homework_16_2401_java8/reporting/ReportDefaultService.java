package homework_16_2401_java8.reporting;

import homework_16_2401_java8.cargo.domain.BasicCargo;
import homework_16_2401_java8.cargo.service.CargoService;
import homework_16_2401_java8.carrier.domain.Carrier;
import homework_16_2401_java8.carrier.service.CarrierService;
import homework_16_2401_java8.common.company_exceptions.checked.ReportExceptions;
import homework_16_2401_java8.transportation.domain.Transportation;
import homework_16_2401_java8.transportation.service.TrsService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static homework_13_3012.common.util.CollectionUtils.isNotEmpty;

public class ReportDefaultService implements ReportService {

    private final CargoService cargoService;
    private final CarrierService carrierService;
    private final TrsService transportationService;

    public ReportDefaultService(
            CargoService cargoService,
            CarrierService carrierService,
            TrsService transportationService) {
        this.cargoService = cargoService;
        this.carrierService = carrierService;
        this.transportationService = transportationService;
    }

    @Override
    public void exportData() throws ReportExceptions {
        List<String> reportData = new ArrayList<>();
        reportData.addAll(getCargosReportData());
        reportData.addAll(getCarriersReportData());
        reportData.addAll(getTransportationsReportData());

        if (isNotEmpty(reportData)) {
            try {
                exportDataToFile(reportData);
            } catch (Exception e) {
                throw new ReportExceptions(e.getMessage());
            }
        }
    }

    private void exportDataToFile(List<String> data) throws IOException {
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile("report", "all-data");
            Files.write(tempFile, data);

            List<String> fileContent = Files.readAllLines(tempFile);
            System.out.println("File content");
            for (String line : fileContent) {
                System.out.println(line);
            }
        } finally {
            if (tempFile != null) {
                Files.delete(tempFile);
            }
        }
    }

    private List<String> getCargosReportData() {
        List<BasicCargo> cargos = cargoService.getAll();

        List<String> result = Collections.emptyList();
        if (isNotEmpty(cargos)) {
            result = new ArrayList<>();

            for (BasicCargo cargo : cargos) {
                if (cargo != null) {
                    result.add(cargoAsString(cargo));
                }
            }
        }

        return result;
    }

    private String cargoAsString(BasicCargo cargo) {
        return "id:" + cargo.getId() + "| " + "Name:" + cargo.getName() + "| weight:" + cargo
                .getWeight() + "| type: " + cargo.getCargoType();
    }

    private List<String> getCarriersReportData() {
        List<Carrier> carriers = carrierService.getAll();

        List<String> result = Collections.emptyList();
        if (isNotEmpty(carriers)) {
            result = new ArrayList<>();

            for (Carrier carrier : carriers) {
                if (carrier != null) {
                    result.add(carrierAsString(carrier));
                }
            }
        }

        return result;
    }


    private String carrierAsString(Carrier carrier) {
        return "id:" + carrier.getId() + " |name:" + carrier.getName() + "| address:" + carrier
                .getAddress();
    }


    private List<String> getTransportationsReportData() {
        List<Transportation> transportations = transportationService.getAll();

        List<String> result = Collections.emptyList();
        if (isNotEmpty(transportations)) {
            result = new ArrayList<>();

            for (Transportation transportation : transportations) {
                if (transportation != null) {
                    result.add(transportationAsString(transportation));
                }
            }
        }

        return result;
    }

    private String transportationAsString(Transportation transportation) {
        return "id:" + transportation.getId() + "| description: " + transportation.getDescription()
                + "| cargoRef: " + transportation.getCargo().getId() + " carrierRef: " + transportation
                .getCarrier().getId();
    }
}

