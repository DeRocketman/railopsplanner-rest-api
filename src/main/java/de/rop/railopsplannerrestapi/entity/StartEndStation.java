package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class StartEndStation extends TrackStation{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_ref")
    private Track track;


}
