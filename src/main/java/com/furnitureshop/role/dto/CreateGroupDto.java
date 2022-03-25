package com.furnitureshop.role.dto;


import com.furnitureshop.role.validation.annotation.UniqueGroupName;
import com.furnitureshop.role.validation.annotation.ValidDescription;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class CreateGroupDto {
	@NotBlank(message = "{group.name.not-blank}")
	@Size(min = 3, max = 50, message = "{role.name.size}")
	@UniqueGroupName(message = "{group.name.used}")
	private String name;
	
	@ValidDescription
	private String description;

}
