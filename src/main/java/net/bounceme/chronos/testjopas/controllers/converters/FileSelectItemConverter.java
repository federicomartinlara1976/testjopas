package net.bounceme.chronos.testjopas.controllers.converters;

import java.io.File;

import javax.faces.model.SelectItem;

import net.bounceme.chronos.utils.assemblers.GenericAssembler;

public class FileSelectItemConverter extends GenericAssembler<File, SelectItem> {

	public FileSelectItemConverter() {
		super(File.class, SelectItem.class);
	}

	@Override
	public SelectItem assemble(File source) {
		return new SelectItem(source.getName(), source.getName());
	}
}
