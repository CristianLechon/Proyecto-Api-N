package ec.edu.uce.ProyectoNasaMars.controller;

import ec.edu.uce.ProyectoNasaMars.downloader.MarsData;
import ec.edu.uce.ProyectoNasaMars.model.MarsPhoto;
import ec.edu.uce.ProyectoNasaMars.service.MarsPhotoService;
import ec.edu.uce.ProyectoNasaMars.service.MarsPhotoServiceImpl;

import java.util.List;


public class Container {

    private MarsData marsData;
    private MarsPhotoService marsPhotoService;

    public Container() {
        marsData = new MarsData();
        marsPhotoService = new MarsPhotoServiceImpl();
    }

    public List<MarsPhoto> loadTable() throws Exception {
        return marsData.getAllMarsPhotos();
    }

    public List<MarsPhoto> getByDateSequential(List<MarsPhoto> marsPhotos, String aux) throws Exception {
        long inicioDeTarea = System.currentTimeMillis();
        List<MarsPhoto> photos = marsPhotoService.filterByDateSequential(marsPhotos, aux);
        long finDeTarea = System.currentTimeMillis();
        long duracionTarea = finDeTarea - inicioDeTarea;
        System.out.println("Duracion del filtado por fecha secuencial: " + duracionTarea + " ms");
        return photos;
    }

    public List<MarsPhoto> getByDateParallel(String aux) throws Exception {
        long inicioDeTarea = System.currentTimeMillis();
        List<MarsPhoto> photos = marsPhotoService.filterByDateParallel(marsData.getAllMarsPhotos(), aux);
        long finDeTarea = System.currentTimeMillis();
        long duracionTarea = finDeTarea - inicioDeTarea;
        System.out.println("Duracion del filtado por fecha paralela: " + duracionTarea + " ms");
        return photos;
    }

    public List<MarsPhoto> getByIdSequential(int id) throws Exception {
        long inicioDeTarea = System.currentTimeMillis();
        List<MarsPhoto> photos = marsPhotoService.filterByIdSequential(marsData.getAllMarsPhotos(), id);
        long finDeTarea = System.currentTimeMillis();
        long duracionTarea = finDeTarea - inicioDeTarea;
        System.out.println("Duracion del filtrado por Id secuencial: " + duracionTarea + " ms");
        return photos;
    }

    public List<MarsPhoto> getByIdParallel(int id) throws Exception {
        long inicioDeTarea = System.currentTimeMillis();
        List<MarsPhoto> photos = marsPhotoService.filterByIdParallel(marsData.getAllMarsPhotos(), id);
        long finDeTarea = System.currentTimeMillis();
        long duracionTarea = finDeTarea - inicioDeTarea;
        System.out.println("Duracion del filtrado por Id paralela: " + duracionTarea + " ms");
        return photos;
    }

    public List<MarsPhoto> getByNameSequential(String aux) throws Exception {
        long inicioDeTarea = System.currentTimeMillis();
        List<MarsPhoto> photos = marsPhotoService.filterByNameSequential(marsData.getAllMarsPhotos(), aux);
        long finDeTarea = System.currentTimeMillis();
        long duracionTarea = finDeTarea - inicioDeTarea;
        System.out.println("Duracion del filtrado por nombre secuencial: " + duracionTarea + " ms");
        return photos;
    }

    public List<MarsPhoto> getByNameParallel(String aux) throws Exception {
        long inicioDeTarea = System.currentTimeMillis();
        List<MarsPhoto> photos = marsPhotoService.filterByNameParallel(marsData.getAllMarsPhotos(), aux);
        long finDeTarea = System.currentTimeMillis();
        long duracionTarea = finDeTarea - inicioDeTarea;
        System.out.println("Duracion del filtrado por nombre paralela: " + duracionTarea + " ms");
        return photos;
    }

}
