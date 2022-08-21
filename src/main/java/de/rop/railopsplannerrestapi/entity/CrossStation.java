package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class CrossStation extends TrackStation{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_ref")
    private Track track;
}
