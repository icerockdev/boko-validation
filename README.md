# Validation rules for konform

[konform](https://github.com/konform-kt/konform)

## Installation

````kotlin
// Append repository
repositories {
    mavenCentral()
}

// Append dependency
implementation("io.konform:konform:X.X.X")
implementation("com.icerockdev:konform-constraints:0.1.0")
````

## Usage

See [konform](https://github.com/konform-kt/konform) docs for more information

Available constraints:
- [date](src/main/kotlin/com/icerockdev/konformConstraints/date.kt) - Constraint for validate string for minimum and maximum length
- [email](src/main/kotlin/com/icerockdev/konformConstraints/email.kt) - Constraint for validate email address
- [inList](src/main/kotlin/com/icerockdev/konformConstraints/inList.kt) - Constraint for validate that the value is in list
- [inRange](src/main/kotlin/com/icerockdev/konformConstraints/inRange.kt) - Constraint for validate that the value is in the range
- [match](src/main/kotlin/com/icerockdev/konformConstraints/match.kt) - Constraint for validate by any matching rule
- [minMaxLength](src/main/kotlin/com/icerockdev/konformConstraints/minMaxLength.kt) - Constraint for validate string for minimum and maximum length

Available extension:
- [rule](src/main/kotlin/com/icerockdev/konformConstraints/extension/rule.kt) - Apply externally created constraint

## TODO

- [ ] Add tests

## Contributing

All development (both new features and bug fixes) is performed in the `develop` branch. This way `master` always
contains the sources of the most recently released version. Please send PRs with bug fixes to the `develop` branch.
Documentation fixes in the markdown files are an exception to this rule. They are updated directly in `master`.

The `develop` branch is pushed to `master` on release.

For more details on contributing please see the [contributing guide](CONTRIBUTING.md).

## License

    Copyright 2020 IceRock MAG Inc.
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
