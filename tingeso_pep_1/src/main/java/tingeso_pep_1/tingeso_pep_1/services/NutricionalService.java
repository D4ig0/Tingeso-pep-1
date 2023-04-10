package tingeso_pep_1.tingeso_pep_1.services;

import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tingeso_pep_1.tingeso_pep_1.entities.AcopioEntity;
import tingeso_pep_1.tingeso_pep_1.entities.NutricionalEntity;
import tingeso_pep_1.tingeso_pep_1.repositories.AcopioRepository;
import tingeso_pep_1.tingeso_pep_1.repositories.NutricionalRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class NutricionalService {


        @Autowired
        private NutricionalRepository nutricionalRepository;

        private final Logger logg = LoggerFactory.getLogger(tingeso_pep_1.tingeso_pep_1.services.NutricionalService.class);

        public ArrayList<NutricionalEntity> obtenerData(){
            return (ArrayList<NutricionalEntity>) nutricionalRepository.findAll();
        }

        @Generated
        public String guardar(MultipartFile file){
            String filename = file.getOriginalFilename();
            if(filename != null){
                if(!file.isEmpty()){
                    try{
                        byte [] bytes = file.getBytes();
                        Path path  = Paths.get(file.getOriginalFilename());
                        Files.write(path, bytes);
                        logg.info("Archivo guardado");
                    }
                    catch (IOException e){
                        logg.error("ERROR", e);
                    }
                }
                return "Archivo guardado con exito!";
            }
            else{
                return "No se pudo guardar el archivo";
            }
        }

        @Generated
        public void leerCsv(String direccion){
            String texto = "";
            BufferedReader bf = null;
            nutricionalRepository.deleteAll();
            try{
                bf = new BufferedReader(new FileReader(direccion));
                String temp = "";
                String bfRead;
                int count = 1;
                while((bfRead = bf.readLine()) != null){
                    if (count == 1){
                        count = 0;
                    }
                    else{
                        guardarDataDB(bfRead.split(";")[0], bfRead.split(";")[1], bfRead.split(";")[2]);
                        temp = temp + "\n" + bfRead;
                    }
                }
                texto = temp;
                System.out.println("Archivo leido exitosamente");
            }catch(Exception e){
                System.err.println("No se encontro el archivo");
            }finally{
                if(bf != null){
                    try{
                        bf.close();
                    }catch(IOException e){
                        logg.error("ERROR", e);
                    }
                }
            }
        }

        public void guardarData(NutricionalEntity data){
            nutricionalRepository.save(data);
        }


        public void guardarDataDB(String proveedor,String grasa, String  solido){
            NutricionalEntity newData = new NutricionalEntity();
            newData.setProveedor(proveedor);
            newData.setGrasa(Integer.parseInt(grasa));
            newData.setSolido(Integer.parseInt(solido));
            guardarData(newData);
        }
        public void eliminarData(ArrayList<NutricionalEntity> datas){
            nutricionalRepository.deleteAll();
        }
    }

