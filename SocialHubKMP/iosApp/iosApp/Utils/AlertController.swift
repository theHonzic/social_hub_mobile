//
//  AlertController.swift
//  iosApp
//
//  Created by Jan Janovec on 22.01.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

class AlertController: ObservableObject {
    @Published var info: AlertInfo?
    
    func displayAlert(_ alert: AlertInfo) {
        self.info = alert
    }
    
    func hideAlert() {
        self.info = nil
    }
}

struct AlertInfo: Identifiable {
    let id: UUID = .init()
    let title: String
    @ViewBuilder let message: () -> AnyView
    @ViewBuilder let actions: () -> AnyView
}

struct AlertPresenter: ViewModifier {
    @StateObject var alertController: AlertController
    
    func body(content: Content) -> some View {
        content
            .alert("CO", isPresented: Binding(get: {
                alertController.info != nil
            }, set: { isPresented in
                if !isPresented {
                    alertController.hideAlert()
                }
            }), presenting: alertController.info) { info in
                AnyView(info.message())
            } message: { info in
                AnyView(info.actions())
            }
    }
}

extension View {
    func alertPresenting(_ controller: AlertController) -> some View {
        modifier(AlertPresenter(alertController: controller))
            .environmentObject(controller)
    }
}
