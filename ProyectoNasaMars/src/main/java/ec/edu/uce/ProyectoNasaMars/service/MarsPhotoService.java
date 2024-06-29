package ec.edu.uce.ProyectoNasaMars.service;

import ec.edu.uce.ProyectoNasaMars.model.MarsPhoto;

import java.util.List;

public interface MarsPhotoService {
    List<MarsPhoto> filterByIdSequential(List<MarsPhoto> photos, int id);

    List<MarsPhoto> filterByIdParallel(List<MarsPhoto> photos, int id);

    List<MarsPhoto> filterByDateSequential(List<MarsPhoto> photos, String date);

    List<MarsPhoto> filterByDateParallel(List<MarsPhoto> photos, String date);

    List<MarsPhoto> filterByNameSequential(List<MarsPhoto> photos, String name);

    List<MarsPhoto> filterByNameParallel(List<MarsPhoto> photos, String name);
}
