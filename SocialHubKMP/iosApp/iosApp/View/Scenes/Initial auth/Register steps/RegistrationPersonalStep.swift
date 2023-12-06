//
//  RegistrationPersonalStep.swift
//  iosApp
//
//  Created by Jan Janovec on 24.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct RegistrationPersonalStep: View {
    @State var selectedCountry: Country = CountryHelper().getAll().first!
    var body: some View {
        VStack(alignment: .leading, spacing: 14) {
            HStack {
                VStack(alignment: .leading, spacing: 20) {
                    Text("Let's get to know each other")
                        .font(.largeTitle)
                        .fontWeight(.heavy)
                        .foregroundStyle(Color.white)
                        .padding(.top, 30)
                    Text("Tell us little bit more.")
                        .foregroundStyle(Color.gray)
                }
                Spacer()
            }
            HStack {
                VStack(alignment: .leading, spacing: 10) {
                    Text("First name*")
                        .foregroundStyle(Color.white)
                    SingleLineTextField(text: .constant(""), placeholder: "Jan", background: .ultraThinMaterial, foreground: .ultraThickMaterial, icon: .init(systemName: "person"))
                }
                VStack(alignment: .leading, spacing: 10) {
                    Text("Last name*")
                        .foregroundStyle(Color.white)
                    SingleLineTextField(text: .constant(""), placeholder: "Janovec", background: .ultraThinMaterial, foreground: .ultraThickMaterial, icon: .init(systemName: "person"))
                }
            }
            VStack(alignment: .leading, spacing: 10) {
                Text("Gender*")
                    .foregroundStyle(Color.white)
                SingleLineTextField(text: .constant(""), placeholder: "Male", background: .ultraThinMaterial, foreground: .ultraThickMaterial, icon: .init(systemName: "eye"))
            }
            VStack(alignment: .leading, spacing: 10) {
                Text("Country*")
                    .foregroundStyle(Color.white)
                SelectInputFIeld(selectedItem: $selectedCountry, list: CountryHelper().getAll())
            }
            Spacer()
        }
    }
}

#Preview {
    InitialAuthView(currentAuthPage: .register)
}
