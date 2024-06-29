package ec.edu.uce.ProyectoNasaMars.service;

import ec.edu.uce.ProyectoNasaMars.model.MarsPhoto;

import java.util.List;
import java.util.stream.Collectors;

public class MarsPhotoServiceImpl implements MarsPhotoService {

    @Override
    public List<MarsPhoto> filterByIdSequential(List<MarsPhoto> photos, int id) {
        List<MarsPhoto> filteredPhotos = photos.stream()
                .filter(photo -> photo.getId() == id)
                .collect(Collectors.toList());

        return filteredPhotos;

    }

    @Override
    public List<MarsPhoto> filterByIdParallel(List<MarsPhoto> photos, int id) {
        List<MarsPhoto> filteredPhotos = photos.stream().parallel()
                .filter(photo -> photo.getId() == id)
                .collect(Collectors.toList());

        return filteredPhotos;
    }

    @Override
    public List<MarsPhoto> filterByDateSequential(List<MarsPhoto> photos, String date) {
        List<MarsPhoto> filteredPhotos = photos.stream()
                .filter(photo -> photo.getEarth_date().equals(date))
                .collect(Collectors.toList());

        return filteredPhotos;
    }

    @Override
    public List<MarsPhoto> filterByDateParallel(List<MarsPhoto> photos, String date) {
        List<MarsPhoto> filteredPhotos = photos.stream().parallel()
                .filter(photo -> photo.getEarth_date().equals(date))
                .collect(Collectors.toList());

        return filteredPhotos;
    }

    @Override
    public List<MarsPhoto> filterByNameSequential(List<MarsPhoto> photos, String name) {
        List<MarsPhoto> filteredPhotos = photos.stream()
                .filter(photo -> photo.getCamera().getName().equals(name))
                .collect(Collectors.toList());

        return filteredPhotos;
    }

    @Override
    public List<MarsPhoto> filterByNameParallel(List<MarsPhoto> photos, String name) {
        List<MarsPhoto> filteredPhotos = photos.stream().parallel()
                .filter(photo -> photo.getCamera().getName().equals(name))
                .collect(Collectors.toList());

        return filteredPhotos;
    }
}
